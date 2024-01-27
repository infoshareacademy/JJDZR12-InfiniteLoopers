package com.isa.webapp.repository;

import com.isa.webapp.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface GradeRepository extends JpaRepository<Grade, UUID> {
    @Query("select g from Grade g where g.user.uuid=:uuid")
    List<Grade> findByUserUuid(String uuid);
}
