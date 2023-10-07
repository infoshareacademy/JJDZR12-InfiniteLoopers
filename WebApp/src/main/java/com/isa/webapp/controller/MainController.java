package com.isa.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getHomePage () {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginaPage () {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage () {
        return "registration";
    }
}
