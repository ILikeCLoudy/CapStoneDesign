package com.EuljiUniv.CapstoneDesign.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.http.ResponseEntity;
import com.EuljiUniv.CapstoneDesign.Entity.ResponseEntity;

import java.util.Optional;

public interface ResponseRepository extends JpaRepository<ResponseEntity, Long> {
    //@Query(value = "select * from answer as a order by ID", nativeQuery = true)
    Optional<ResponseEntity> findById(Long ID);
}
