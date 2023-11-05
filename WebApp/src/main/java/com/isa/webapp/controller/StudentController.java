package com.isa.webapp.controller;

import com.isa.webapp.model.Subjects;
import com.isa.webapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private UserService userService;

        @GetMapping("/student/dashboard")
        public String studentDashboard(Model model, HttpSession session) {
            Map<Subjects, List<Integer>> grades = userService.getGradesForLoggedInUser(session);
            if (!grades.isEmpty()) {
                model.addAttribute("grades", grades);
                return "student_dashboard";
            } else {
                return "redirect:/login";
            }
        }
    }



