package com.meta.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "emotion_note")
@NoArgsConstructor
@Data
public class EmotionNote {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "note_id")
    private Note note;

    public EmotionNote(Long id, Emotion emotion, Note note) {
        this.id = id;
        this.emotion = emotion;
        this.note = note;
    }
}
