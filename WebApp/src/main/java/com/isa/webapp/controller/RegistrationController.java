package com.isa.webapp.controller;

import com.isa.webapp.model.User;
import com.isa.webapp.service.UserManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final UserManager userManager;

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userManager.registerUser(user);
            return "registrationSuccessPage";
        } catch (IOException e) {
            e.printStackTrace();
            return "errorPage";
        } catch (IllegalStateException e) {
            model.addAttribute("content", "registration");
            model.addAttribute("errorMessage", "E-mail już istnieje");
            return "registration";
        }
    }
}