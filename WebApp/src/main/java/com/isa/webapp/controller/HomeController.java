package com.isa.webapp.controller;

import com.isa.webapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("content", "index");
        return "main";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("content", "registration");
        return "main";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("content", "login");
        return "main";
    }

    @GetMapping("/announcement")
    public String getAnnouncementPage (Model model){
        model.addAttribute("content", "announcement");
        return "main";
    }
}