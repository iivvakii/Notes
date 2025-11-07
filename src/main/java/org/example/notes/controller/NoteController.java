package org.example.notes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.notes.dto.*;
import org.example.notes.model.Tag;
import org.example.notes.service.NoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/all")
    public Page<NoteShortResponseDto> findAll(
            @RequestParam(required = false) List<Tag> tags,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
        NoteFilter filter = generateNodeFilter(tags, page, size, sortDirection);
        return noteService.findAll(filter);
    }

    @GetMapping
    public NoteResponseDto getById(@RequestParam String id){
        return noteService.getNote(id);
    }

    @PostMapping
    public void createNote(@RequestBody @Valid NoteRequestDto request){
        noteService.create(request);
    }

    @PutMapping
    public void updateNote(@RequestBody NoteUpdateRequestDto request){
        noteService.update(request);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@RequestParam String id){
        noteService.delete(id);
    }

    @GetMapping("/stats")
    public StatsResponseDto getStats(@RequestParam String id){
        return noteService.countStats(id);
    }

    private NoteFilter generateNodeFilter(List<Tag> tags, int page, int size, Sort.Direction sortDirection) {
        NoteFilter filter = new NoteFilter();
        filter.setTags(tags);
        filter.setPage(page);
        filter.setSize(size);
        filter.setSortDirection(sortDirection);
        return filter;
    }

}
