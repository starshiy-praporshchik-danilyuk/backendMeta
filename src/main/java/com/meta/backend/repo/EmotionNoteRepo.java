package com.meta.backend.repo;

import com.meta.backend.entity.EmotionNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmotionNoteRepo extends JpaRepository<EmotionNote, Long> {

    List<EmotionNote> getAllByNote_Id(Long noteId);
}
