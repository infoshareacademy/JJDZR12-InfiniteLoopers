package com.isa.webapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


public class User implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
