package com.meta.backend.service;

import com.meta.backend.converter.EmotionNoteConverter;
import com.meta.backend.converter.NoteConverter;
import com.meta.backend.converter.PointNoteConverter;
import com.meta.backend.dto.*;
import com.meta.backend.entity.EmotionNote;
import com.meta.backend.entity.Note;
import com.meta.backend.entity.PointNote;
import com.meta.backend.repo.EmotionNoteRepo;
import com.meta.backend.repo.NoteRepo;
import com.meta.backend.repo.PointNoteRepo;
import com.meta.backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private UserRepo userRepo;

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

    public ResponseNoteListDto getAllNotesByUsername(String username, Pageable pageable) throws Exception {
        if (!userRepo.existsByUsername(username))
            throw new Exception("Пользователя с данным именем не существует");
        Long userId = userRepo.findByUsername(username).getId();
        long countNotes = noteRepo.countAllByUser_Id(userId);
        List<NoteDto> notes = noteRepo.getAllByUser_Id(userId, pageable)
                .stream()
                .map(x -> noteConverter.toDto(x))
                .collect(Collectors.toList());
        return ResponseNoteListDto.builder()
                .notes(notes)
                .numPage(pageable.getPageNumber())
                .countPages((long) Math.ceil((double) countNotes / pageable.getPageSize()))
                .build();
    }

    public NoteDto getNoteById(Long id){
        return noteConverter.toDto(noteRepo.getById(id));
    }

    @Transactional
    public NoteDto updateNote(NoteDto noteDto) throws Exception {
        var oldNote = noteRepo.findById(noteDto.getId())
                .orElseThrow(() -> new Exception("Записи с данным ID не существует"));
        if (noteDto.getDate().equals(oldNote.getDateOfCreate()))
            oldNote.setDateOfCreate(noteDto.getDate());
        pointNoteRepo.deleteAllByNote_Id(noteDto.getId());
        for (PointNoteDto pointNoteDto : noteDto.getPointNotes()) {
            pointNoteDto.setNoteId(noteDto.getId());
        }
        List<PointNote> newPointNote = noteDto.getPointNotes().stream()
                .map(x -> pointNoteConverter.toEntity(x))
                .collect(Collectors.toList());
        pointNoteRepo.saveAll(newPointNote);
        emotionNoteRepo.deleteAllByNote_Id(noteDto.getId());
        for (EmotionNoteDto emotionNoteDto : noteDto.getEmotionNotes()) {
            emotionNoteDto.setNoteId(noteDto.getId());
        }
        List<EmotionNote> newEmotionNote = noteDto.getEmotionNotes().stream()
                .map(x -> emotionNoteConverter.toEntity(x))
                .collect(Collectors.toList());
        emotionNoteRepo.saveAll(newEmotionNote);
        return getNoteById(noteDto.getId());
    }

    @Transactional
    public Long deleteNote(Long id) throws Exception {
        if (!noteRepo.existsById(id))
            throw new Exception("Записи с данным ID не существует");
        emotionNoteRepo.deleteAllByNote_Id(id);
        pointNoteRepo.deleteAllByNote_Id(id);
        noteRepo.deleteById(id);
        return id;
    }
}
