package com.isa.webapp.service;

import com.isa.webapp.model.Grade;
import com.isa.webapp.model.Subject;
import com.isa.webapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final Map<String, Map<Subject, List<Integer>>> studentGrades = new HashMap<>();

    private static final String USERS_JSON_FILE = "users.json";


    public Map<Subject, List<Double>> getGradesForStudent(String studentId) {
        return studentRepository.findByUuid(studentId)
                .map(user -> user.getGrades().stream().collect(Collectors.groupingBy(Grade::getSubjects,
                        Collectors.mapping(Grade::getValue,
                                Collectors.toList())))).orElse(Map.of());
    }

/*    private void loadStudentGradesFromJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<User> users = objectMapper.readValue(new File(USERS_JSON_FILE), new TypeReference<>() {});
            for (User user : users) {
                studentGrades.put(user.getUuid(), user.getGrades());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
