package com.meta.backend.controller;

import com.meta.backend.dto.NoteDto;
import com.meta.backend.dto.ResponseNoteListDto;
import com.meta.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/note")
    public NoteDto createNote(@RequestBody NoteDto noteDto){
        return noteService.createNote(noteDto);
    }

    @GetMapping("/note/{username}/notes")
    public ResponseNoteListDto getAllNotes(@PathVariable("username") String username, Pageable pageable){
        return noteService.getAllNotesByUsername(username, pageable);
    }

    @GetMapping("/note/{id}")
    public NoteDto getNote(@PathVariable("id") Long id){
        return noteService.getNoteById(id);
    }
}
