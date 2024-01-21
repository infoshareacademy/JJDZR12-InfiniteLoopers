package com.isa.webapp.service;

import com.isa.webapp.model.Grade;
import com.isa.webapp.model.Subject;
import com.isa.webapp.model.User;
import com.isa.webapp.repository.GradeRepository;
import com.isa.webapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private GradeRepository gradeRepository;

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
}