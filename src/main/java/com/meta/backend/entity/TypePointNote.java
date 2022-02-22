package com.meta.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "types_point_note")
@NoArgsConstructor
@Data
public class TypePointNote {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "code")
    private String code;

    public TypePointNote(Long id, String code) {
        this.id = id;
        this.code = code;
    }
}
