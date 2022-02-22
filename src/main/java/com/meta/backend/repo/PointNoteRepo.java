package com.meta.backend.repo;

import com.meta.backend.entity.PointNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointNoteRepo extends JpaRepository<PointNote, Long> {
}
