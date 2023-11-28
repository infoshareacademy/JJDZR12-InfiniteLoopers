package com.isa.webapp.controller;

import com.isa.webapp.model.Subjects;
import com.isa.webapp.model.User;
import com.isa.webapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    private final UserService userService;

    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/student/dashboard")
    public String studentDashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Map<Subjects, List<Integer>> grades = userService.getGradesForLoggedInUser((User) userDetails);
        if (!grades.isEmpty()) {
            model.addAttribute("grades", grades);
            boolean isStudent = userDetails.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("STUDENT"));
            User user = (User) userDetails;
            model.addAttribute("isStudent", isStudent);
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
            return "student_dashboard";
        } else {
            return "redirect:/login";
        }
    }
}
