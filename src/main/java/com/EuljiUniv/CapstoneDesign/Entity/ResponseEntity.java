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
    private int Interest1;
    @Column(nullable = false)
    private int Interest2;
    @Column(nullable = false)
    private int Interest3;
    @Column(nullable = false)
    private int Interest4;
    @Column(nullable = false)
    private int Interest5;

    @Column(nullable = false)
    private int MajorLike1;
    @Column(nullable = false)
    private int MajorLike2;
    @Column(nullable = false)
    private int MajorLike3;
    @Column(nullable = false)
    private int MajorLike4;
    @Column(nullable = false)
    private int MajorLike5;

    @Column(nullable = false)
    private int  Job_Employ1;
    @Column(nullable = false)
    private int Job_Employ2;
    @Column(nullable = false)
    private int Job_Employ3;
    @Column(nullable = false)
    private int Job_Employ4;
    @Column(nullable = false)
    private int Job_Employ5;

    @Column
    private int Result;





}
