package com.isa.webapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class LoginController {

    private static final String USERS_JSON_FILE = "users.json";

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, HttpSession session) {
        if (loginUser(user)) {
            redirectAttributes.addFlashAttribute("successMessage", "Zalogowano pomyślnie!");

            User registeredUser = getUserFromRegistrationData(user);

            if (registeredUser != null) {
                user.setGrades(registeredUser.getGrades());
                user.setUserRole(registeredUser.getUserRole());
                session.setAttribute("loggedInUser", user);
            }

            if (user.getUserRole() == UserRole.STUDENT) {
                return "redirect:/student/dashboard";
            } else if (user.getUserRole() == UserRole.TEACHER) {
                return "redirect:/teacher/students";
            } else {
                return "redirect:/";
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Błąd logowania. Spróbuj ponownie.");
            return "redirect:/login";
        }
    }

    private User getUserFromRegistrationData(User user) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = mapper.readValue(Files.newBufferedReader(Paths.get(USERS_JSON_FILE)), new TypeReference<>() {
            });

            for (User registeredUser : users) {
                if (user.getEmail().equals(registeredUser.getEmail())) {
                    return registeredUser;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean loginUser(User user) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = mapper.readValue(Files.newBufferedReader(Paths.get(USERS_JSON_FILE)), new TypeReference<>() {
            });

            for (User storedUser : users) {
                if (user.getEmail().equals(storedUser.getEmail()) && user.getPassword().equals(storedUser.getPassword())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
