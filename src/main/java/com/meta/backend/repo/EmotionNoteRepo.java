package com.meta.backend.repo;

import com.meta.backend.entity.EmotionNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionNoteRepo extends JpaRepository<EmotionNote, Long> {
}
