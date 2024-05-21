package com.EuljiUniv.CapstoneDesign.Service;

import com.EuljiUniv.CapstoneDesign.Entity.ResponseEntity;
import com.EuljiUniv.CapstoneDesign.Repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResponseService {
    private final ResponseRepository responseRepository;

    public void insertdata(ResponseEntity responseEntity) {
        responseRepository.save(responseEntity);
    }

    public ResponseEntity response(Long id) {
        //long x = 100;
        Optional<ResponseEntity> responseEntity = responseRepository.findById(id);
        return responseEntity.orElse(null);
    }
}
