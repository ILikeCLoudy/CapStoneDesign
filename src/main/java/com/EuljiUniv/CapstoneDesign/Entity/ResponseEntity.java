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
    private Long ID;

    @Column
    private String Person;

    @Column
    private String StudentID;

    @Column
    private String Major;

    @Column
    private String Sex;

    @Column
    private String MBTI;

    @Column(nullable = false)
    private int Bigdata1;
    @Column(nullable = false)
    private int Bigdata2;
    @Column(nullable = false)
    private int Bigdata3;
    @Column(nullable = false)
    private int Bigdata4;
    @Column(nullable = false)
    private int Bigdata5;
    @Column(nullable = false)
    private int Bigdata6;
    @Column(nullable = false)
    private int Bigdata7;
    @Column(nullable = false)
    private int Bigdata8;
    @Column(nullable = false)
    private int Bigdata9;

    @Column(nullable = false)
    private int MedicalMG1;
    @Column(nullable = false)
    private int MedicalMG2;
    @Column(nullable = false)
    private int MedicalMG3;
    @Column(nullable = false)
    private int MedicalMG4;
    @Column(nullable = false)
    private int MedicalMG5;
    @Column(nullable = false)
    private int MedicalMG6;
    @Column(nullable = false)
    private int MedicalMG7;
    @Column(nullable = false)
    private int MedicalMG8;
    @Column(nullable = false)
    private int MedicalMG9;

    @Column(nullable = false)
    private int MedicalEG1;
    @Column(nullable = false)
    private int MedicalEG2;
    @Column(nullable = false)
    private int MedicalEG3;
    @Column(nullable = false)
    private int MedicalEG4;
    @Column(nullable = false)
    private int MedicalEG5;
    @Column(nullable = false)
    private int MedicalEG6;
    @Column(nullable = false)
    private int MedicalEG7;
    @Column(nullable = false)
    private int MedicalEG8;
    @Column(nullable = false)
    private int MedicalEG9;





}
