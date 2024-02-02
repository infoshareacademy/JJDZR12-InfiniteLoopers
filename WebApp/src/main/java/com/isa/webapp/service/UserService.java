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
public class UserService {

    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> getUsersByRole(UserRole role) {
        return userRepository.findAllByUserRole(role);
    }

    public Map<Subject, List<Double>> getGradesForLoggedInUser(User user) {
        List<Grade> grades = gradeRepository.findByUserUuid(user.getUuid());
        return grades.stream()
                .collect(Collectors.groupingBy(
                        Grade::getSubjects,
                        Collectors.mapping(Grade::getValue, Collectors.toList())
                ));
    }

    public static Map<Subject, List<Double>> convertGradesToMap(List<Grade> grades) {
        return grades.stream().collect(Collectors.groupingBy(Grade::getSubjects, Collectors.mapping(Grade::getValue, Collectors.toList())));
    }

    public void registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("Uzytkownik z takim email instnieje");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
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
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateUserRole(Long userId, UserRole newRole) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found with id: " + userId));
        user.setUserRole(newRole);
        userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found with id: " + userId));
    }

    public void updateUser(User updatedUser) {
        User existingUser = userRepository.findById(updatedUser.getId()).orElseThrow(() ->
                new RuntimeException("User not found with id: " + updatedUser.getId()));
        userRepository.save(existingUser);
    }

    public List<User> getUnapprovedUsers() {
        return userRepository.findAllByIsApproved(false);
    }


}