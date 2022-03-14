package com.meta.backend.service;

import com.meta.backend.converter.EmotionNoteConverter;
import com.meta.backend.converter.NoteConverter;
import com.meta.backend.converter.PointNoteConverter;
import com.meta.backend.dto.EmotionNoteDto;
import com.meta.backend.dto.NoteDto;
import com.meta.backend.dto.PointNoteDto;
import com.meta.backend.dto.ResponseNoteListDto;
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

import java.time.LocalDate;
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

    public ResponseNoteListDto getAllNotesByUsername(String username, Pageable pageable){
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
}
