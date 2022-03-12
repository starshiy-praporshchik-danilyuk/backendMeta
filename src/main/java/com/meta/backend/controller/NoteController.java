package com.meta.backend.controller;

import com.meta.backend.dto.NoteDto;
import com.meta.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/note/{username}/{date}")
    public List<NoteDto> getAllNotes(@PathVariable("username") String username,
                                     @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                     Pageable pageable){
        return noteService.getAllNotesByUsernameAndDate(username, date, pageable);
    }

    @GetMapping("/note/{id}")
    public NoteDto getNote(@PathVariable("id") Long id){
        return noteService.getNoteById(id);
    }
}
