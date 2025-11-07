package org.example.notes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.notes.model.Tag;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequestDto {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Text is required")
    private String text;
    private Tag tag;
}
