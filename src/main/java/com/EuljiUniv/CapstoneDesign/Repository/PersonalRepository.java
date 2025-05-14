package com.EuljiUniv.CapstoneDesign.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.http.ResponseEntity;
import com.EuljiUniv.CapstoneDesign.Entity.PersonalEntity;

import java.util.Optional;

public interface PersonalRepository extends JpaRepository<PersonalEntity, Long> {
    Optional<PersonalEntity> findByStudentid(String studentid);
    PersonalEntity findFirstByStudentid(String studentid);
}
