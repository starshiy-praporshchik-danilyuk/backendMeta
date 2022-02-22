package com.meta.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notes")
@NoArgsConstructor
@Data
public class Note {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "date_of_create")
    private LocalDate dateOfCreate;

    @Column(name = "date_of_update")
    private LocalDate dateOfUpdate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Note(Long id, LocalDate dateOfCreate, LocalDate dateOfUpdate, User user) {
        this.id = id;
        this.dateOfCreate = dateOfCreate;
        this.dateOfUpdate = dateOfUpdate;
        this.user = user;
    }
}
