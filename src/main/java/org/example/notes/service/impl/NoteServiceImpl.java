package org.example.notes.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.notes.builder.NoteQueryBuilder;
import org.example.notes.dto.*;
import org.example.notes.exception.NotFoundException;
import org.example.notes.mapper.NoteMapper;
import org.example.notes.mapper.StatsMapper;
import org.example.notes.model.Note;
import org.example.notes.repository.NoteRepository;
import org.example.notes.service.NoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final MongoTemplate mongoTemplate;
    private final StatsMapper statsMapper;

    @Override
    public Page<NoteShortResponseDto> findAll(NoteFilter filter) {
        Query query = NoteQueryBuilder.build(filter);
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());
        query.with(pageable);
        List<Note> notes = mongoTemplate.find(query, Note.class);

        List<NoteShortResponseDto> list = notes.stream().map(noteMapper::noteToNoteShortDto).toList();
        long count = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Note.class);

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public NoteResponseDto getNote(String id){
        Note note = getById(id);
        return noteMapper.noteToNoteDto(note);
    }

    @Override
    @Transactional
    public void create(NoteRequestDto request){
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        note.setTag(request.getTag());
        note.setDate(LocalDateTime.now());
        noteRepository.save(note);
    }

    @Override
    @Transactional
    public void update(NoteUpdateRequestDto request){
        Note note = getById(request.getId());
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        note.setTag(request.getTag());
        noteRepository.save(note);
    }

    @Override
    @Transactional
    public void delete(String id){
        noteRepository.deleteById(id);
    }

    @Override
    public StatsResponseDto countStats(String id){
        Note note = getById(id);
        Map<String, Long> stats = getNoteWordStats(note.getText());
        return statsMapper.generateStatsResponseDto(id,  stats);
    }

    private Map<String, Long> getNoteWordStats(String noteText) {
        if (noteText == null || noteText.isBlank()) {
            return Collections.emptyMap();
        }

        String cleaned = noteText
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-zA-Zа-яА-Я0-9\\s]", "");

        Map<String, Long> counts = Arrays.stream(cleaned.split("\\s+"))
                .filter(w -> !w.isBlank())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return counts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }


    private Note getById(String id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with id=" + id + " not found"));
    }
}
