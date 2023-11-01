package com.isa.webapp.controller;

import com.isa.webapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("content", "index");
        return "main";
    }
    @GetMapping("/login")
    String getLoginPage(Model model) {
        model.addAttribute("content", "login");
        return "main";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        User user = new User(); // Tworzenie nowego obiektu User
        model.addAttribute("user", user); // Dodawanie obiektu do modelu
        model.addAttribute("content", "registration"); // Ustawienie treści na "registration"
        return "main"; // Zwracanie widoku "main"
    }

}
