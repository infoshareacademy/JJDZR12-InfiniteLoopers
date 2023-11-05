package com.isa.webapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class User {

    private final String id = UUID.randomUUID().toString();
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String schoolName;
    private UserRole userRole;
    private Map<Subjects, List<Integer>> grades;

    public User() {
        this.grades = new HashMap<>();
        grades.put(Subjects.POLSKI, new ArrayList<>());
        grades.put(Subjects.WF, new ArrayList<>());
        grades.put(Subjects.BIOLOGIA, new ArrayList<>());
        grades.put(Subjects.MATEMATYKA, new ArrayList<>());
        grades.put(Subjects.ANGIELSKI, new ArrayList<>());
        grades.put(Subjects.SZTUKA, new ArrayList<>());
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Map<Subjects, List<Integer>> getGrades() {
        return grades;
    }

    public void setGrades(Map<Subjects, List<Integer>> grades) {
        this.grades = grades;
    }
}
