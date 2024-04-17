package com.EuljiUniv.CapstoneDesign.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import jakarta.persistence.criteria.From;
import java.util.List;
import java.util.Optional;

public interface ResponseRepository extends JpaRepository<ResponseEntity, Long> {
    @Query(value = "select * from answer as a order by ID", nativeQuery = true)
    public ResponseEntity findBy();
}
