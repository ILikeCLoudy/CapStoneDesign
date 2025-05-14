package com.EuljiUniv.CapstoneDesign.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {
    private String name;
    private String studentid;
    private int result;

    @Builder
    public AnswerRequest(String name ,String studentid,int result) {
        this.name = name;
        this.studentid = studentid;
        this.result = result;
    }
}
