package com.EuljiUniv.CapstoneDesign.Repository;

import com.EuljiUniv.CapstoneDesign.Entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {
    Optional<SurveyEntity> findByStudentid(String studentid);
    List<SurveyEntity> findAllByStudentid(String studentid);

    SurveyEntity findFirstByStudentidOrderByIdDesc(String studentid);
}
