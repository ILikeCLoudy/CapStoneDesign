package com.EuljiUniv.CapstoneDesign.Service;

import com.EuljiUniv.CapstoneDesign.Repository.SurveyRepository;
import com.EuljiUniv.CapstoneDesign.Entity.SurveyEntity;
import com.EuljiUniv.CapstoneDesign.DTO.ResultRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    public SurveyEntity save(SurveyEntity request) {
        return surveyRepository.save(request);
    }

    public List<SurveyEntity> findAllByStudentid(String studentid) {
        return surveyRepository.findAllByStudentid(studentid);
    }

    public List<SurveyEntity> findAll() {
        return surveyRepository.findAll();
    }

    /*public List<SurveyEntity> findFirstByStudentid(String studentid) {
        return surveyRepository.findFirstByStudentidOrderByIdDesc(studentid);
    }*/

    public SurveyEntity findnewByStudentid(String studentid) {
        return surveyRepository.findFirstByStudentidOrderByIdDesc(studentid);
    }

    @Transactional
    public SurveyEntity updateResult(ResultRequest request) {
        SurveyEntity updateSurveyEntity = surveyRepository.findFirstByStudentidOrderByIdDesc(request.getStudentid());
        updateSurveyEntity.UpdateResult(request.getResult());

        return updateSurveyEntity;
    }

    @Transactional
    public SurveyEntity updateResult(SurveyEntity request) {
        SurveyEntity updateSurveyEntity = surveyRepository.findFirstByStudentidOrderByIdDesc(request.getStudentid());
        updateSurveyEntity.UpdateResult(request.getResult());

        return updateSurveyEntity;
    }
}
