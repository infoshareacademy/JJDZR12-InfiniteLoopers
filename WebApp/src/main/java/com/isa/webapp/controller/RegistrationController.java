package com.isa.webapp.controller;

import com.isa.webapp.model.User;
import com.isa.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            return "registrationSuccessPage";
        } catch (IllegalStateException e) {
            model.addAttribute("content", "registration");
            model.addAttribute("errorMessage", "E-mail już istnieje");
            return "registration";
        }
    }
}