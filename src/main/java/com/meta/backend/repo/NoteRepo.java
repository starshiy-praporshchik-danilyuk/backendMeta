package com.meta.backend.repo;

import com.meta.backend.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note, Long> {
}
