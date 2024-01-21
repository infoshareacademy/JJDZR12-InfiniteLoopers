package com.isa.webapp.controller;

import com.isa.webapp.model.*;
import com.isa.webapp.repository.GradeRepository;
import com.isa.webapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final UserRepository userRepository;

    private final GradeRepository gradeRepository;

    @GetMapping("/teacher/students")
    public String showStudentList(Model model, @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        boolean isTeacher = userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("TEACHER"));
        User user = (User) userDetails;
        /*        List<User> students = getStudentsForTeacher();*/
        List<User> students = userRepository.findAllByUserRole(UserRole.STUDENT);
        model.addAttribute("students", students);
        model.addAttribute("isTeacher", isTeacher);
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        return "teacher_student_list";
    }

    @GetMapping("/teacher/add-grade/{studentUuid}")
    public String showAddGradeForm(@PathVariable String studentUuid, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        boolean isTeacher = userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("TEACHER"));
        User student = userRepository.findByUuid(studentUuid).orElse(null);
        GradeForm gradeForm = new GradeForm();
        gradeForm.setStudentId(studentUuid);
        GradeForm user = new GradeForm();
        model.addAttribute("gradeForm", gradeForm);
        model.addAttribute("subjects", Subject.values());
        model.addAttribute("isTeacher", isTeacher);
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName()); //TODO fix

        return "teacher_add_grade";
    }

    @GetMapping("/teacher/view-grades/{studentId}")//todo
    public String viewGrades(@PathVariable String studentId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        boolean isTeacher = userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("TEACHER"));
        List<Grade> studentGrades = gradeRepository.findByUserUuid(studentId);
        Map<Subject, List<Double>> grades = convertGradesToMap(studentGrades);
        model.addAttribute("grades", grades);
        model.addAttribute("isTeacher", isTeacher); //TODO fix
        return "teacher_view_grades";
    }

    @PostMapping("/teacher/add-grade")
    public String addGrade(@RequestParam("studentId") String studentUuid, @RequestParam("subject") Subject subject, @RequestParam("grade") Double grade, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUuid(studentUuid).orElse(null);
        Grade newGrade = new Grade();
        newGrade.setSubjects(subject);
        newGrade.setValue(grade);
        newGrade.setDate(LocalDate.now());
        newGrade.setUser(user);
        gradeRepository.save(newGrade);
        return "redirect:/teacher/students";
    }

    public Map<Subject, List<Double>> convertGradesToMap(List<Grade> grades) {
        return grades.stream().collect(Collectors.groupingBy(Grade::getSubjects, Collectors.mapping(Grade::getValue, Collectors.toList())));
    }
}