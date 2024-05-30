package com.EuljiUniv.CapstoneDesign.Entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)


public class PersonalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String person;

    @Column(unique = true)
    private String studentid;

    @Column
    private String major;

    @Column
    private String sex;

    @Column
    private String mbti;

    @Builder
    public PersonalEntity(String person,String studentid,String major,String sex, String mbti) {
        this.person = person;
        this.studentid = studentid;
        this.sex = sex;
        this.major = major;
        this.mbti = mbti;
    }
}
