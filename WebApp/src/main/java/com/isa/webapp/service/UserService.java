package com.isa.webapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.webapp.model.Subjects;
import com.isa.webapp.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private static final String USERS_JSON_FILE = "/home/user/Desktop/JJDZR12-InfiniteLoopers/WebApp/src/main/resources/users.json";

    public Map<Subjects, List<Integer>> getGradesForLoggedInUser(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        System.out.println(loggedInUser);

        if (loggedInUser != null && loggedInUser.getGrades() != null && !loggedInUser.getGrades().isEmpty()) {
            return loggedInUser.getGrades();
        }
        System.out.println("nie ma ocen");
        return Collections.emptyMap(); // W przypadku braku ocen lub błędu, zwróć pustą mapę
    }
}

