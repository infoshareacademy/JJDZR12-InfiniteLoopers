/*
package com.isa.webapp.repository;

import com.isa.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<User, Long> {

    @Query("select u from User u left join fetch u.grades where u.uuid=:uuid")
    Optional<User> findByUuid(@Param("uuid") String uuid);

}
*/
