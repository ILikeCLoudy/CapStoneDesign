package com.EuljiUniv.CapstoneDesign.Service;

import com.EuljiUniv.CapstoneDesign.Entity.PersonalEntity;
import com.EuljiUniv.CapstoneDesign.Repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class PersonalService {

    @Autowired
    PersonalRepository personalRepository;

    public PersonalEntity save(PersonalEntity request) {
        return personalRepository.save(request);
    }

    public Optional<PersonalEntity> findPersonalEntityByStudentid(String studentid) {
        return personalRepository.findByStudentid(studentid);
    }

    public PersonalEntity findnewByStudentid(String studentid) {
        return personalRepository.findFirstByStudentid(studentid);
    }
}
