package com.isa.webapp.service;

import com.isa.webapp.model.Grade;
import com.isa.webapp.model.Subject;
import com.isa.webapp.model.User;
import com.isa.webapp.repository.GradeRepository;
import com.isa.webapp.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) {
        LOGGER.debug(() -> "Fetching user by email: " + email);
        return userRepository.findByEmail(email).orElse(null);
    }

    public Map<Subject, List<Double>> getGradesForLoggedInUser(User user) {
        LOGGER.debug(() -> "Getting grades for user: " + user.getEmail());
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
        LOGGER.info(() -> "Registering user: " + user.getEmail());
        if (userRepository.existsByEmail(user.getEmail())) {
            LOGGER.warn(() -> "User with email " + user.getEmail() + " already exists");
            throw new IllegalStateException("Uzytkownik z takim email instnieje");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        LOGGER.info(() -> "User " + user.getEmail() + " registered successfully");
    }

    public Optional<User> findUserByEmail(String email) {
        LOGGER.debug(() -> "Finding user by email: " + email);
        return userRepository.findByEmail(email);
    }
}