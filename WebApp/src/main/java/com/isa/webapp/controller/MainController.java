package com.isa.webapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainController {

    @ModelAttribute("content")
    public String content(final HttpServletRequest request) {
        return request.getRequestURI().equals("/") ? "index" :
                request.getRequestURI().substring(1);
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "main";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "main";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "main";
    }
}
