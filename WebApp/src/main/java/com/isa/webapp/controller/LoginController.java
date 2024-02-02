package com.isa.webapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import com.isa.webapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
    private final UserService userService;

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, HttpSession session) {
        LOGGER.debug(() -> "Attempt to login by user: " + user.getEmail());
        User registeredUser = getUserFromRegistrationData(user);

        if (registeredUser != null) {
            LOGGER.info(() -> "Successful login attempt for user: " + user.getEmail());
            user.setGrades(registeredUser.getGrades());
            user.setUserRole(registeredUser.getUserRole());
            session.setAttribute("loggedInUser", user);

            if (user.getUserRole() == UserRole.STUDENT) {
                LOGGER.debug(() -> "Redirecting to student dashboard for user: " + user.getEmail());
                return "redirect:/student/dashboard";
            } else if (user.getUserRole() == UserRole.TEACHER) {
                LOGGER.debug(() -> "Redirecting to teacher students list for user: " + user.getEmail());
                return "redirect:/teacher/students";
            } else {
                LOGGER.debug(() -> "Redirecting to home for user: " + user.getEmail());
                return "redirect:/";
            }
        } else {
            LOGGER.warn(() -> "Failed login attempt for user: " + user.getEmail());
            redirectAttributes.addFlashAttribute("errorMessage", "Błąd logowania. Spróbuj ponownie.");
            return "redirect:/login";
        }
    }

    private User getUserFromRegistrationData(User user) {
        return userService.getUserByEmail(user.getEmail());
    }
}
