package com.meta.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {

    private Long id;
    private String username;
    private LocalDate date;
    private List<PointNoteDto> pointNotes;
    private List<EmotionNoteDto> emotionNotes;
}
