package org.example.notes.service;

import org.example.notes.dto.*;
import org.springframework.data.domain.Page;

public interface NoteService {
    Page<NoteShortResponseDto> findAll(NoteFilter filter);

    NoteResponseDto getNote(String id);

    void create(NoteRequestDto request);

    void update(NoteUpdateRequestDto request);

    void delete(String id);

    StatsResponseDto countStats(String id);
}
