package com.meta.backend.repo;

import com.meta.backend.entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepo extends JpaRepository<Emotion, Long> {
    Emotion getEmotionByCode(String code);
}
