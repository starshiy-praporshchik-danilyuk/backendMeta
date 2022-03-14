package com.meta.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNoteListDto {

    private List<NoteDto> notes;
    private Integer numPage;
    private Long countPages;
}
