package com.isa.webapp.controller;

import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import com.isa.webapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@Slf4j
public class LoginController {

    private final UserService userService;

    @PostMapping("/sign-in")
    public String postLogin(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, HttpSession session) {
        User registeredUser = getUserFromRegistrationData(user);

        if (registeredUser != null) {
            log.info("Successful login attempt for user: " + user.getEmail());
            user.setGrades(registeredUser.getGrades());
            user.setUserRole(registeredUser.getUserRole());
            session.setAttribute("loggedInUser", user);
          
            if (user.getUserRole() == UserRole.ADMIN) {
               return user.getPassword().equals("admin")
                       ? "redirect:/admin/edit-profile"
                       : "redirect:/";
            } else if (user.getUserRole() == UserRole.STUDENT) {
                log.debug("Redirecting to home page for user: " + user.getEmail());
                return "redirect:/";
            } else if (user.getUserRole() == UserRole.TEACHER) {
                log.debug("Redirecting to home page for user: " + user.getEmail());
                return "redirect:/";
            } else {
                log.debug("Redirecting to home page for user: " + user.getEmail());
                return "redirect:/";
            }
        } else {
            log.warn("Failed login attempt for user: " + user.getEmail());
            redirectAttributes.addFlashAttribute("errorMessage", "Błąd logowania. Spróbuj ponownie.");
            return "redirect:/login";
        }
    }

    private User getUserFromRegistrationData(User user) {
        return userService.getUserByEmail(user.getEmail());
    }
}
