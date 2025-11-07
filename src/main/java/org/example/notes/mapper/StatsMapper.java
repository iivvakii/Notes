package org.example.notes.mapper;

import org.example.notes.dto.StatsResponseDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StatsMapper {

    public StatsResponseDto generateStatsResponseDto(String id, Map<String, Long> stats) {
        StatsResponseDto statsResponseDto = new StatsResponseDto();
        statsResponseDto.setId(id);
        statsResponseDto.setCounts(stats);
        return statsResponseDto;
    }
}
