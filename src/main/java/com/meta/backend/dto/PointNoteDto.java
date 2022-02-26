package com.meta.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointNoteDto {

    private Long id;
    private String content;
    private Long noteId;
    private String typePointNote;
}
