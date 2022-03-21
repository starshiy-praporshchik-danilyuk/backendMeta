package com.meta.backend.repo;

import com.meta.backend.entity.PointNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointNoteRepo extends JpaRepository<PointNote, Long> {

    List<PointNote> getAllByNote_Id(Long noteId);
    void deleteAllByNote_Id(Long noteId);
}
