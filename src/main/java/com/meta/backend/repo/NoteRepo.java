package com.meta.backend.repo;

import com.meta.backend.entity.Note;
import com.meta.backend.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepo extends JpaRepository<Note, Long> {

    List<Note> getAllByUser_Id(Long userId, Pageable pageable);
}
