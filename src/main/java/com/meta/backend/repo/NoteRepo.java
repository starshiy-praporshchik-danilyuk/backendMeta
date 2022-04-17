package com.meta.backend.repo;

import com.meta.backend.entity.Note;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NoteRepo extends JpaRepository<Note, Long> {

    List<Note> getAllByUser_Id(Long userId, Pageable pageable);
    Long countAllByUser_Id(Long userId);
    List<Note> getAllByDateOfCreateAfterAndDateOfCreateBeforeAndUser_Id(LocalDate startDate, LocalDate endDate, Long userId, Pageable pageable);
    Long countAllByDateOfCreateAfterAndDateOfCreateBeforeAndUser_Id(LocalDate startDate, LocalDate endDate, Long userId);
}
