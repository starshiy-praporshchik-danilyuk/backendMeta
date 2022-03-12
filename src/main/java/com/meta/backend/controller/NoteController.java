package com.meta.backend.controller;

import com.meta.backend.dto.NoteDto;
import com.meta.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/note")
    public NoteDto createNote(@RequestBody NoteDto noteDto){
        return noteService.createNote(noteDto);
    }

    @GetMapping("/note/{username}")
    public List<NoteDto> getAllNotes(@RequestHeader("Authorization") String auth, @PathVariable("username") String username, Pageable pageable){
        return noteService.getAllNotes(username, pageable);
    }
}
