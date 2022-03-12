package com.meta.backend.repo;

import com.meta.backend.entity.Note;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NoteRepo extends JpaRepository<Note, Long> {

    List<Note> getAllByUser_IdAndDateOfCreate(Long userId, LocalDate date, Pageable pageable);
}
