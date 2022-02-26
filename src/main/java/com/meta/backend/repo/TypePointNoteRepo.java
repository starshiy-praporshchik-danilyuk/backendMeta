package com.meta.backend.repo;

import com.meta.backend.entity.TypePointNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePointNoteRepo extends JpaRepository<TypePointNote, Long> {

    TypePointNote getTypePointNoteByCode(String code);
}
