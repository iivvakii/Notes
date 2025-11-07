package org.example.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.notes.model.Tag;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteUpdateRequestDto {
    private String id;
    private String title;
    private String text;
    private Tag tag;
}