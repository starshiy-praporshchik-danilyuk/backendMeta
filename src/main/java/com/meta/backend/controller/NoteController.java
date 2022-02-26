package com.meta.backend.controller;

import com.meta.backend.dto.NoteDto;
import com.meta.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/note")
    public NoteDto createNote(@RequestBody NoteDto noteDto){
        return noteService.createNote(noteDto);
    }
}
