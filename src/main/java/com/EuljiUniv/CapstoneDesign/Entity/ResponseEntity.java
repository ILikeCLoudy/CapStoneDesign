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
    private String StudentID;

    @Column
    private String Major;

    @Column
    private String Sex;

    @Column
    private String MBTI;

    @Column
    private String Bigdata1;
    @Column
    private String Bigdata2;
    @Column
    private String Bigdata3;
    @Column
    private String Bigdata4;
    @Column
    private String Bigdata5;
    @Column
    private String Bigdata6;
    @Column
    private String Bigdata7;
    @Column
    private String Bigdata8;
    @Column
    private String Bigdata9;

    @Column
    private String MedicalMG1;
    @Column
    private String MedicalMG2;
    @Column
    private String MedicalMG3;
    @Column
    private String MedicalMG4;
    @Column
    private String MedicalMG5;
    @Column
    private String MedicalMG6;
    @Column
    private String MedicalMG7;
    @Column
    private String MedicalMG8;
    @Column
    private String MedicalMG9;

    @Column
    private String MedicalEG1;
    @Column
    private String MedicalEG2;
    @Column
    private String MedicalEG3;
    @Column
    private String MedicalEG4;
    @Column
    private String MedicalEG5;
    @Column
    private String MedicalEG6;
    @Column
    private String MedicalEG7;
    @Column
    private String MedicalEG8;
    @Column
    private String MedicalEG9;





}
