package com.meta.backend.service;

import com.meta.backend.converter.EmotionNoteConverter;
import com.meta.backend.converter.NoteConverter;
import com.meta.backend.converter.PointNoteConverter;
import com.meta.backend.dto.EmotionNoteDto;
import com.meta.backend.dto.NoteDto;
import com.meta.backend.dto.PointNoteDto;
import com.meta.backend.entity.EmotionNote;
import com.meta.backend.entity.Note;
import com.meta.backend.entity.PointNote;
import com.meta.backend.repo.EmotionNoteRepo;
import com.meta.backend.repo.NoteRepo;
import com.meta.backend.repo.PointNoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;
    @Autowired
    private PointNoteRepo pointNoteRepo;
    @Autowired
    private PointNoteConverter pointNoteConverter;
    @Autowired
    private EmotionNoteConverter emotionNoteConverter;
    @Autowired
    private NoteConverter noteConverter;
    @Autowired
    private EmotionNoteRepo emotionNoteRepo;

    public NoteDto createNote(NoteDto noteDto){
        Note newNote = noteRepo.save(noteConverter.toEntity(noteDto));
        noteDto.setId(newNote.getId());
        for (PointNoteDto pointNoteDto : noteDto.getPointNotes()) {
            pointNoteDto.setNoteId(newNote.getId());
        }
        List<PointNote> pointNotes = noteDto.getPointNotes()
                .stream()
                .map(pointNoteConverter::toEntity)
                .collect(Collectors.toList());
        pointNoteRepo.saveAll(pointNotes);
        for (EmotionNoteDto emotionNoteDto : noteDto.getEmotionNotes()){
            emotionNoteDto.setNoteId(newNote.getId());
        }
        List<EmotionNote> emotionNotes = noteDto.getEmotionNotes()
                .stream()
                .map(x -> emotionNoteConverter.toEntity(x))
                .collect(Collectors.toList());
        emotionNoteRepo.saveAll(emotionNotes);

        return noteConverter.toDto(newNote);
    }
}
