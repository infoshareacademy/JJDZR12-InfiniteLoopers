package com.isa.webapp.service;


import com.isa.webapp.model.Subjects;
import com.isa.webapp.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    public Map<Subjects, List<Integer>> getGradesForLoggedInUser(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null && loggedInUser.getGrades() != null && !loggedInUser.getGrades().isEmpty()) {
            return loggedInUser.getGrades();
        }

        return Collections.emptyMap();
    }
}

