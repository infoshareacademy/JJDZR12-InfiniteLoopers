package com.isa.webapp.controller;

import com.isa.webapp.model.User;
import com.isa.webapp.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class UserController {

    private final UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute User user) {
        try {
            userManager.registerUser(user);
            return "registrationSuccessPage";
        } catch (IOException e) {
            e.printStackTrace();
            return "errorPage";
        }
    }
}