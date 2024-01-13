package com.isa.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String uuid = UUID.randomUUID().toString();
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String schoolName;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

/*    private Map<Subjects, List<Integer>> grades;*/

/*    public User() {
        this.grades = new HashMap<>();
        grades.put(Subjects.POLSKI, new ArrayList<>());
        grades.put(Subjects.WF, new ArrayList<>());
        grades.put(Subjects.BIOLOGIA, new ArrayList<>());
        grades.put(Subjects.MATEMATYKA, new ArrayList<>());
        grades.put(Subjects.ANGIELSKI, new ArrayList<>());
        grades.put(Subjects.SZTUKA, new ArrayList<>());
    }*/

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    @JsonIgnore
    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userRole != null) {
            return Collections.singleton(new SimpleGrantedAuthority(userRole.name()));
        } else {
            return Collections.emptyList();
        }
    }

/*    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userRole.name()));
    }*/

    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
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
        return Collections.emptyMap(); //grades
    }

    public void setGrades(Map<Subjects, List<Integer>> grades) {
/*        this.grades = grades;*/
    }
}
