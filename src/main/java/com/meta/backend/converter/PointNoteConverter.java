package com.meta.backend.converter;

import com.meta.backend.dto.PointNoteDto;
import com.meta.backend.entity.PointNote;
import com.meta.backend.repo.NoteRepo;
import com.meta.backend.repo.TypePointNoteRepo;
import org.springframework.stereotype.Component;

@Component
public class PointNoteConverter {

    private final NoteRepo noteRepo;
    private final TypePointNoteRepo typePointNoteRepo;

    public PointNoteConverter(NoteRepo noteRepo, TypePointNoteRepo typePointNoteRepo) {
        this.noteRepo = noteRepo;
        this.typePointNoteRepo = typePointNoteRepo;
    }

    public PointNote toEntity(PointNoteDto pointNoteDto){
        return PointNote.builder()
                .id(pointNoteDto.getId())
                .content(pointNoteDto.getContent())
                .note(noteRepo.getById(pointNoteDto.getNoteId()))
                .typePointNote(typePointNoteRepo.getTypePointNoteByCode(pointNoteDto.getTypePointNote()))
                .build();
    }

    public PointNoteDto toDto(PointNote pointNote){
        return PointNoteDto.builder()
                .id(pointNote.getId())
                .typePointNote(pointNote.getTypePointNote().getCode())
                .content(pointNote.getContent())
                .noteId(pointNote.getNote().getId())
                .build();
    }
}
