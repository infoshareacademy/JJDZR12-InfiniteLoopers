package com.isa.webapp.repository;

import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllByUserRole(UserRole role);

    boolean existsByEmail(String email);
}
