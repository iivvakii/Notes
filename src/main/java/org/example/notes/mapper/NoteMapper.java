package org.example.notes.mapper;

import lombok.RequiredArgsConstructor;
import org.example.notes.dto.NoteResponseDto;
import org.example.notes.dto.NoteShortResponseDto;
import org.example.notes.model.Note;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteMapper {

    public NoteResponseDto noteToNoteDto(Note note){
        NoteResponseDto response = new NoteResponseDto();
        response.setId(note.getId());
        response.setTitle(note.getTitle());
        response.setText(note.getText());
        response.setCreatedDate(note.getDate().toString());
        response.setTag(note.getTag());
        return response;
    }

    public NoteShortResponseDto noteToNoteShortDto(Note note){
        NoteShortResponseDto response = new NoteShortResponseDto();
        response.setId(note.getId());
        response.setTitle(note.getTitle());
        response.setDateCreation(note.getDate().toString());
        return response;
    }
}
