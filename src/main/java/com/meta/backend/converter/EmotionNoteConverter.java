package com.meta.backend.converter;

import com.meta.backend.dto.EmotionNoteDto;
import com.meta.backend.entity.EmotionNote;
import com.meta.backend.repo.EmotionRepo;
import com.meta.backend.repo.NoteRepo;
import org.springframework.stereotype.Component;

@Component
public class EmotionNoteConverter {

    private final EmotionRepo emotionRepo;
    private final NoteRepo noteRepo;

    public EmotionNoteConverter(EmotionRepo emotionRepo, NoteRepo noteRepo) {
        this.emotionRepo = emotionRepo;
        this.noteRepo = noteRepo;
    }

    public EmotionNote toEntity(EmotionNoteDto emotionNoteDto){
        return EmotionNote.builder()
                .id(emotionNoteDto.getId())
                .emotion(emotionRepo.getEmotionByCode(emotionNoteDto.getEmotionCode()))
                .note(noteRepo.getById(emotionNoteDto.getNoteId()))
                .build();
    }

    public EmotionNoteDto toDto(EmotionNote emotionNote){
        return EmotionNoteDto.builder()
                .id(emotionNote.getId())
                .noteId(emotionNote.getNote().getId())
                .emotionCode(emotionNote.getEmotion().getCode())
                .build();
    }
}
