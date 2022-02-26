package com.meta.backend.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notes")
@NoArgsConstructor
@Data
@Builder
public class Note {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "date_of_create")
    private LocalDate dateOfCreate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Note(Long id, LocalDate dateOfCreate, User user) {
        this.id = id;
        this.dateOfCreate = dateOfCreate;
        this.user = user;
    }
}
