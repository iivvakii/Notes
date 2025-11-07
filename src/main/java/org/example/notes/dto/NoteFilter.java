package org.example.notes.dto;

import lombok.Data;
import org.example.notes.model.Tag;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
public class NoteFilter {
    private List<Tag> tags;
    private int page = 0;
    private int size = 10;
    private String sortBy = "date";
    private Sort.Direction sortDirection = Sort.Direction.DESC;
}
