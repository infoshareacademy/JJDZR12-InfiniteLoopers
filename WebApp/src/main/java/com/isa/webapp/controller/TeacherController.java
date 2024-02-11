package com.isa.webapp.controller;

import com.isa.webapp.dto.GradeFormDto;
import com.isa.webapp.model.*;
import com.isa.webapp.repository.GradeRepository;
import com.isa.webapp.repository.UserRepository;
import com.isa.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TeacherController {

    private static final Logger LOGGER = LogManager.getLogger(TeacherController.class);
    private final UserRepository userRepository;

    private final GradeRepository gradeRepository;

    @GetMapping("/teacher/students")
    public String showStudentList(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        LOGGER.info(() -> "Showing student list for teacher: " + userDetails.getUsername());
        User user = (User) userDetails;
        boolean isTeacher = userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("TEACHER"));
        if (isTeacher) {
            List<User> students = userRepository.findAllByUserRoleAndSchoolName(UserRole.STUDENT, user.getSchoolName());
            model.addAttribute("students", students);
        }
        model.addAttribute("isTeacher", isTeacher);
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("schoolName", user.getSchoolName());

        return "teacher_student_list";
    }

    @GetMapping("/teacher/add-grade/{studentUuid}")
    public String showAddGradeForm(@PathVariable String studentUuid, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        LOGGER.info(() -> "Showing add grade form for studentUuid: " + studentUuid + ", by teacher: " + userDetails.getUsername());
        boolean isTeacher = userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("TEACHER"));
        GradeFormDto gradeForm = new GradeFormDto();
        gradeForm.setStudentId(studentUuid);
        GradeFormDto user = new GradeFormDto();
        model.addAttribute("gradeForm", gradeForm);
        model.addAttribute("subjects", Subject.values());
        model.addAttribute("isTeacher", isTeacher);
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());

        return "teacher_add_grade";
    }

    @GetMapping("/teacher/view-grades/{studentId}")
    public String viewGrades(@PathVariable String studentId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        LOGGER.info(() -> "Viewing grades for studentId: " + studentId + ", by teacher: " + userDetails.getUsername());
        boolean isTeacher = userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("TEACHER"));
        List<Grade> studentGrades = gradeRepository.findByUserUuid(studentId);
        Map<Subject, List<Double>> grades = UserService.convertGradesToMap(studentGrades);
        model.addAttribute("grades", grades);
        model.addAttribute("isTeacher", isTeacher);
        return "teacher_view_grades";
    }

    @PostMapping("/teacher/add-grade")
    public String addGrade(@RequestParam("studentId") String studentUuid, @RequestParam("subject") Subject subject, @RequestParam("grade") Double grade) {
        User user = userRepository.findByUuid(studentUuid).orElse(null);
        LOGGER.info(() -> "Teacher adding grade for studentId: " + studentUuid);
        Grade newGrade = new Grade();
        newGrade.setSubjects(subject);
        newGrade.setValue(grade);
        newGrade.setDate(LocalDate.now());
        newGrade.setUser(user);
        gradeRepository.save(newGrade);
        LOGGER.info(() -> "Grade successfully added for studentId: " + studentUuid);
        return "redirect:/teacher/students";
    }
}