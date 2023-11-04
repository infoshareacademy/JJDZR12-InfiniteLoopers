package com.isa.webapp.controller;

import com.isa.webapp.model.Subjects;
import com.isa.webapp.model.User;
import com.isa.webapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private UserService userService;

        @GetMapping("/student/dashboard")
        public String studentDashboard(Model model, HttpSession session) {
            // Pobierz oceny zalogowanego użytkownika korzystając z UserService
            Map<Subjects, List<Integer>> grades = userService.getGradesForLoggedInUser(session);
            System.out.println(grades);
            if (!grades.isEmpty()) {
                model.addAttribute("grades", grades);
                return "student_dashboard";
            } else {
                // Obsłuż przypadek braku ocen lub braku zalogowanego użytkownika
                return "redirect:/login";
            }
        }
    }



