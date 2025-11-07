package org.example.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.notes.model.Tag;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoteResponseDto {
    private String id;
    private String title;
    private String text;
    private String createdDate;
    private Tag tag;

}
