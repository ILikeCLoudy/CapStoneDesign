package com.EuljiUniv.CapstoneDesign.Entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "responseanswer")

public class ResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String Person;

    @Column
    private String Sex;

    @Column
    private String StudentID;

    @Column
    private String Major;

    @Column
    private String MBTI;

    @Column
    private String first_last;
}
