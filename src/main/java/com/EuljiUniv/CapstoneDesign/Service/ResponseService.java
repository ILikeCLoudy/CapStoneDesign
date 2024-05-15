package com.EuljiUniv.CapstoneDesign.Service;

import com.EuljiUniv.CapstoneDesign.Entity.ResponseEntity;
import com.EuljiUniv.CapstoneDesign.Repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseService {
    private final ResponseRepository responseRepository;

    public void insertdata(ResponseEntity responseEntity) {
        //responseRepository.save(responseEntity);
    }

    /*public ResponseEntity response() {
        ResponseEntity responseEntity = responseRepository.findById();
        return responseEntity;
    }*/
}
