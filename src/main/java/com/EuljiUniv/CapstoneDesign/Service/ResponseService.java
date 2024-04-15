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

    public ResponseEntity response() {
        ResponseEntity responseEntity = responseRepository.findBy();
        //findby는 정의 미구현으로 인해  보류건
        return responseEntity;
    }
}
