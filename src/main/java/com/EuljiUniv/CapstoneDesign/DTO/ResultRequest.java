package com.EuljiUniv.CapstoneDesign.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultRequest {
    private String studentid;
    private int result;

    public ResultRequest(String studentid,int result) {
        this.studentid = studentid;
        this.result = result;
    }
}
