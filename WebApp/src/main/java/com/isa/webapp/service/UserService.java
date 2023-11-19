package com.isa.webapp.service;

import com.isa.webapp.model.Subjects;
import com.isa.webapp.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserManager userManager;

    public UserService(UserManager userManager) {
        this.userManager = userManager;
    }

    public User registerUser(String email, String password, String firstName, String lastName) throws IOException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userManager.registerUser(user);
        return user;
    }

    public Optional<User> loginUser(String email, String password) {
        return userManager.findUserByEmail(email)
                .filter(u -> u.getPassword().equals(password));
    }

    public Map<Subjects, List<Integer>> getGradesForLoggedInUser(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null && loggedInUser.getGrades() != null && !loggedInUser.getGrades().isEmpty()) {
            return loggedInUser.getGrades();
        }

        return Collections.emptyMap();
    }
}