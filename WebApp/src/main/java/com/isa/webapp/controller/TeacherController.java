package com.isa.webapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.webapp.model.GradeForm;
import com.isa.webapp.model.Subjects;
import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class TeacherController {


    private static final String USERS_JSON_FILE = "/home/user/Desktop/JJDZR12-InfiniteLoopers/WebApp/src/main/resources/users.json";

    @GetMapping("/teacher/students")
    public String showStudentList(Model model) throws IOException {
        // Przykładowa logika - pobierz listę uczniów i przekaż ją do widoku
        List<User> students = getStudentsForTeacher();
        model.addAttribute("students", students);
        return "teacher_student_list";
    }

    @GetMapping("/teacher/add-grade/{studentId}")
    public String showAddGradeForm(@PathVariable String studentId, Model model) {
        GradeForm gradeForm = new GradeForm();
        gradeForm.setStudentId(studentId);

        model.addAttribute("gradeForm", gradeForm);
        model.addAttribute("subjects", Subjects.values());

        return "teacher_add_grade";
    }


    @PostMapping("/teacher/add-grade")
    public String addGrade(@ModelAttribute("gradeForm") GradeForm gradeForm) {
        // Przykładowa logika - dodaj ocenę do ocen ucznia
        addGradeToStudent(gradeForm.getStudentId(), gradeForm.getSubject(), gradeForm.getGrade());
        return "redirect:/teacher/students";
    }
    public List<User> getStudentsForTeacher() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.readValue(Files.newBufferedReader(Paths.get(USERS_JSON_FILE)), new TypeReference<>() {});

        return users.stream()
                .filter(user -> user.getUserRole() == UserRole.STUDENT)
                .collect(Collectors.toList());
    }

    public void addGradeToStudent(String studentId, Subjects subject, int grade) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = mapper.readValue(Files.newBufferedReader(Paths.get(USERS_JSON_FILE)), new TypeReference<>() {});

            for (User user : users) {
                if (user.getUserId().equals(studentId)) {
                    Map<Subjects, List<Integer>> grades = user.getGrades();
                    grades.computeIfAbsent(subject, key -> new ArrayList<>()).add(grade);

                    // Aktualizuj dane użytkownika w pliku JSON
                    Files.write(Paths.get(USERS_JSON_FILE), mapper.writeValueAsBytes(users));
                    break; // Przerwij pętlę po znalezieniu ucznia
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}




