package com.isa.webapp.repository;

import com.isa.webapp.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface GradeRepository extends JpaRepository<Grade, UUID> {
    List<Grade> findByUserUuid(String uuid);

}
