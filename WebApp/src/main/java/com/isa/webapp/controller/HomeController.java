package com.isa.webapp.controller;

import com.isa.webapp.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String getIndex(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        boolean isStudent = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("STUDENT"));
        boolean isTeacher = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("TEACHER"));
        User user = (User) userDetails;
        model.addAttribute("isStudent", isStudent);
        model.addAttribute("isTeacher", isTeacher);
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());

        return "index";
    }

    @PostMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        boolean isStudent = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("STUDENT"));
        boolean isTeacher = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("TEACHER"));
        User user = (User) userDetails;
        model.addAttribute("isStudent", isStudent);
        model.addAttribute("isTeacher", isTeacher);
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());

        return "index";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
/*        model.addAttribute("content", "registration");*/
        return "registration";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("content", "login");
        return "login";
    }

    @GetMapping("/announcement")
    public String getAnnouncementPage (Model model){
        model.addAttribute("content", "announcement");
        return "announcement";
    }
}