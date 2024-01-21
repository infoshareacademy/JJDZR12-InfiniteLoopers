package com.isa.webapp.service;

import com.isa.webapp.model.Grade;
import com.isa.webapp.model.Subject;
import com.isa.webapp.model.User;
import com.isa.webapp.repository.GradeRepository;
import com.isa.webapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
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

}