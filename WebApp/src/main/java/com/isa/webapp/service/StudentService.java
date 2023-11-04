package com.isa.webapp.service;

import com.isa.webapp.model.Subjects;
import com.isa.webapp.model.User;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class StudentService {
    private final Map<String, Map<Subjects, List<Integer>>> studentGrades = new HashMap<>();

    private static final String USERS_JSON_FILE = "/home/user/Desktop/JJDZR12-InfiniteLoopers/WebApp/src/main/resources/users.json";

    public Map<Subjects, List<Integer>> getGradesForStudent(String studentId) {
        loadStudentGradesFromJson();
        return studentGrades.getOrDefault(studentId, new HashMap<>());
    }

    private void loadStudentGradesFromJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<User> users = objectMapper.readValue(new File(USERS_JSON_FILE), new TypeReference<>() {});
            for (User user : users) {
                studentGrades.put(user.getUserId(), user.getGrades());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
