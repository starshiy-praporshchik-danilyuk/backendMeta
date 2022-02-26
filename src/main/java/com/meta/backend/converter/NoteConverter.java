package com.meta.backend.converter;

import com.meta.backend.dto.NoteDto;
import com.meta.backend.entity.Note;
import com.meta.backend.repo.EmotionNoteRepo;
import com.meta.backend.repo.PointNoteRepo;
import com.meta.backend.repo.UserRepo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NoteConverter {

    private final UserRepo userRepo;
    private final PointNoteRepo pointNoteRepo;
    private final PointNoteConverter pointNoteConverter;
    private final EmotionNoteRepo emotionNoteRepo;
    private final EmotionNoteConverter emotionNoteConverter;

    public NoteConverter(UserRepo userRepo, PointNoteRepo pointNoteRepo, PointNoteConverter pointNoteConverter, EmotionNoteRepo emotionNoteRepo, EmotionNoteConverter emotionNoteConverter) {
        this.userRepo = userRepo;
        this.pointNoteRepo = pointNoteRepo;
        this.pointNoteConverter = pointNoteConverter;
        this.emotionNoteRepo = emotionNoteRepo;
        this.emotionNoteConverter = emotionNoteConverter;
    }

    public Note toEntity(NoteDto noteDto){
        return Note.builder()
                .id(noteDto.getId())
                .dateOfCreate(noteDto.getDate())
                .user(userRepo.findByUsername(noteDto.getUsername()))
                .build();
    }

    public NoteDto toDto(Note note){
        return NoteDto.builder()
                .id(note.getId())
                .username(note.getUser().getUsername())
                .date(note.getDateOfCreate())
                .pointNotes(pointNoteRepo.getAllByNote_Id(note.getId()).stream().map(pointNoteConverter::toDto).collect(Collectors.toList()))
                .emotionNotes(emotionNoteRepo.getAllByNote_Id(note.getId()).stream().map(emotionNoteConverter::toDto).collect(Collectors.toList()))
                .build();
    }
}
