package com.isa.webapp.controller;

import com.isa.webapp.model.User;
import com.isa.webapp.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userManager.registerUser(user);
            return "registrationSuccessPage";
        } catch (IOException e) {
            e.printStackTrace();
            return "errorPage";
        } catch (IllegalStateException e) {
/*            model.addAttribute("emailError",true);*/
            model.addAttribute("content", "registration");
            model.addAttribute("errorMessage", "E-mail już istnieje");
            //TODO
            return "main";
        }
    }

/*        @GetMapping("/userProfile")
        public String userProfile(Model model) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName();

            model.addAttribute("username", currentUserName);
            return "index";
        }*/
}