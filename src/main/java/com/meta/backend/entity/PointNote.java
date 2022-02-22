package com.meta.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "point_note")
@NoArgsConstructor
@Data
public class PointNote {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "note_id")
    private Note note;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "type_point_note_id")
    private TypePointNote typePointNote;

    public PointNote(Long id, String content, Note note, TypePointNote typePointNote) {
        this.id = id;
        this.content = content;
        this.note = note;
        this.typePointNote = typePointNote;
    }
}
