package com.meta.backend.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "emotions")
@NoArgsConstructor
@Data
@Builder
public class Emotion {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "code")
    private String code;

    public Emotion(Long id, String code) {
        this.id = id;
        this.code = code;
    }
}
