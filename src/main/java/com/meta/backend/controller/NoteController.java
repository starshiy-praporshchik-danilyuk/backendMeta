package com.meta.backend.controller;

import com.meta.backend.dto.NoteDto;
import com.meta.backend.dto.ResponseDto;
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
    public ResponseDto<NoteDto> createNote(@RequestBody NoteDto noteDto){
        return ResponseDto.ok(noteService.createNote(noteDto));
    }

    @GetMapping("/note/{username}/notes")
    public ResponseDto<ResponseNoteListDto> getAllNotes(@PathVariable("username") String username, Pageable pageable) throws Exception {
        return ResponseDto.ok(noteService.getAllNotesByUsername(username, pageable));
    }

    @GetMapping("/note/{id}")
    public ResponseDto<NoteDto> getNote(@PathVariable("id") Long id){
        return ResponseDto.ok(noteService.getNoteById(id));
    }

    @PutMapping("/note")
    public ResponseDto<NoteDto> updateNote(@RequestBody NoteDto noteDto) throws Exception {
        return ResponseDto.ok(noteService.updateNote(noteDto));
    }

    @DeleteMapping("/note/{id}")
    public ResponseDto<Long> deleteNote(@PathVariable("id") Long id) throws Exception {
        return ResponseDto.ok(noteService.deleteNote(id));
    }
}
