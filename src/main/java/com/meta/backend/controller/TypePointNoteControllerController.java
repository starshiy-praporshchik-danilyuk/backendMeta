package com.meta.backend.controller;

import com.meta.backend.dto.TypePointNoteDto;
import com.meta.backend.entity.TypePointNote;
import com.meta.backend.repo.TypePointNoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TypePointNoteControllerController {

    @Autowired
    private TypePointNoteRepo typePointNoteRepo;

    @PostMapping("/typePointNote")
    public TypePointNote addTypePointNote(@RequestBody TypePointNoteDto typePointNoteDto){
        return typePointNoteRepo.save(TypePointNote.builder().code(typePointNoteDto.getCode()).build());
    }
}
