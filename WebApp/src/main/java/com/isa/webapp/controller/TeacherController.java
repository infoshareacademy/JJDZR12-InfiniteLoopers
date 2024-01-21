package com.isa.webapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.webapp.model.*;
import com.isa.webapp.repository.GradeRepository;
import com.isa.webapp.repository.UserRepository;
import com.isa.webapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final StudentService studentService;

    private final UserRepository userRepository;

    private final GradeRepository gradeRepository;

/*    @Autowired
    public TeacherController(StudentService studentService) {
        this.studentService = studentService;
    }*/

    private static final String USERS_JSON_FILE = "users.json";

    @GetMapping("/teacher/students")
    public String showStudentList(Model model, @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        boolean isTeacher = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("TEACHER"));
        User user = (User) userDetails;
/*        List<User> students = getStudentsForTeacher();*/
        List<User> students = userRepository.findAllByUserRole(UserRole.STUDENT);
        model.addAttribute("students", students);
        model.addAttribute("isTeacher", isTeacher);
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        return "teacher_student_list";
    }

    @GetMapping("/teacher/add-grade/{studentUuid}")
    public String showAddGradeForm(@PathVariable String studentUuid,
                                   Model model,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        boolean isTeacher = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("TEACHER"));
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
    public String viewGrades(@PathVariable String studentId,
                             Model model,
                             @AuthenticationPrincipal UserDetails userDetails) {
        boolean isTeacher = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("TEACHER"));
        List<Grade> studentGrades = gradeRepository.findByUserUuid(studentId);
        Map<Subject, List<Double>> grades = convertGradesToMap(studentGrades);

/*
        Map<Subject, List<Double>> grades = studentService.getGradesForStudent(studentId);
*/
        model.addAttribute("grades", grades);
        model.addAttribute("isTeacher", isTeacher); //TODO fix
        return "teacher_view_grades";
    }

    @PostMapping("/teacher/add-grade")
    public String addGrade(@RequestParam("studentId") String studentUuid,
                           @RequestParam("subject") Subject subject,
                           @RequestParam("grade") Double grade,
                           @AuthenticationPrincipal UserDetails userDetails) {
/*    public String addGrade(@ModelAttribute("gradeForm") GradeForm gradeForm) {
        addGradeToStudent(gradeForm.getStudentId(), gradeForm.getSubject(), gradeForm.getGrade());*/
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
        return grades.stream()
                .collect(Collectors.groupingBy(
                        Grade::getSubjects,
                        Collectors.mapping(Grade::getValue, Collectors.toList())
                ));
    }

/*    public List<User> getStudentsForTeacher() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.readValue(Files.newBufferedReader(Paths.get(USERS_JSON_FILE)), new TypeReference<>() {});

        return users.stream()
                .filter(user -> user.getUserRole() == UserRole.STUDENT)
                .collect(Collectors.toList());
    }*/

    /*public void addGradeToStudent(String studentId, Subject subject, int grade) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = mapper.readValue(Files.newBufferedReader(Paths.get(USERS_JSON_FILE)), new TypeReference<>() {});

            for (User user : users) {
                if (user.getId().equals(studentId)) {
                    Map<Subject, List<Integer>> grades = Map.of();
                    grades.computeIfAbsent(subject, key -> new ArrayList<>()).add(grade);
                    Files.write(Paths.get(USERS_JSON_FILE), mapper.writeValueAsBytes(users));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}