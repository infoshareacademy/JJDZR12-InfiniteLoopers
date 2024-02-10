package com.isa.webapp.service;

import com.isa.webapp.model.Grade;
import com.isa.webapp.model.Subject;
import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import com.isa.webapp.repository.GradeRepository;
import com.isa.webapp.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) {
        log.debug("Fetching user by email: {}", email);
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> getUsersByRole(UserRole role) {
        log.debug("Fetching users by role: {}", role);
        return userRepository.findAllByUserRole(role);
    }

    public Map<Subject, List<Double>> getGradesForLoggedInUser(User user) {
        log.debug("Getting grades for user: {}", user.getEmail());
        List<Grade> grades = gradeRepository.findByUserUuid(user.getUuid());
        return grades.stream()
                .collect(Collectors.groupingBy(
                        Grade::getSubjects,
                        Collectors.mapping(Grade::getValue, Collectors.toList())
                ));
    }

    public static Map<Subject, List<Double>> convertGradesToMap(List<Grade> grades) {
        log.debug("Converting grades list to map");
        return grades.stream().collect(Collectors.groupingBy(Grade::getSubjects, Collectors.mapping(Grade::getValue, Collectors.toList())));
    }

    public void registerUser(User user) {
        log.info("Registering user: {}", user.getEmail());
        if (userRepository.existsByEmail(user.getEmail())) {

            log.warn("User with email {} already exists", user.getEmail());
            throw new IllegalStateException("User with that email already exist.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User {} registered successfully", user.getEmail());
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void approveUserRoles(Long userId, List<UserRole> roles) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found with id: " + userId));
        user.setApprovedRoles(roles);
        user.setApproved(true);
        userRepository.save(user);
        log.info("User roles approved for user id: {}", userId);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        log.info("User with id {} deleted successfully", userId);
    }

    public User getUserById(Long userId) {
        log.debug("Fetching user by id: {}", userId);
        return userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found with id: " + userId));
    }

    public void updateUser(User updatedUser) {
        User existingUser = userRepository.findById(updatedUser.getId()).orElseThrow(() ->
                new RuntimeException("User not found with id: " + updatedUser.getId()));
        userRepository.save(existingUser);
        log.info("User with id {} updated successfully", updatedUser.getId());
    }

    public List<User> getUnapprovedUsers() {
        log.debug("Fetching unapproved users");
        return userRepository.findAllByIsApproved(false);
    }

    public boolean isAdmin(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(UserRole.ADMIN.name()));
    }
}
