package com.isa.webapp.model;


import java.util.*;

public class User {

    private String loginUser;
    private String passwordUser;
    private String emailUser;
    private String firstNameUser;
    private String lastNameUser;
    private String nameSchoolUser;

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public void setFirstNameUser(String firstNameUser) {
        this.firstNameUser = firstNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public String getNameSchoolUser() {
        return nameSchoolUser;
    }

    public void setNameSchoolUser(String nameSchoolUser) {
        this.nameSchoolUser = nameSchoolUser;
    }

    private String userId;
    private UserRole userRole;
    private Map<Subjects, List<Integer>> grades;

    public Map<Subjects, List<Integer>> getGrades() {
        return grades;
    }

    public void setGrades(Map<Subjects, List<Integer>> grades) {
        this.grades = grades;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public User () {
        this.grades = new HashMap<>();
        grades.put(Subjects.POLSKI,new ArrayList<>());
        grades.put(Subjects.WF,new ArrayList<>());
        grades.put(Subjects.BIOLOGIA,new ArrayList<>());
        grades.put(Subjects.MATEMATYKA,new ArrayList<>());
        grades.put(Subjects.ANGIELSKI,new ArrayList<>());
        grades.put(Subjects.SZTUKA,new ArrayList<>());
    }


    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getFirstNameUser() {
        return firstNameUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public String getUserId() {
        return userId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Класс: ").append(this.getClass().getSimpleName()).append("\n");
        sb.append("Оценки:\n");

        for (Map.Entry<Subjects, List<Integer>> entry : grades.entrySet()) {
            sb.append(entry.getKey().name()).append(": ").append(entry.getValue()).append("\n");
        }

        return "\nUser{" +
                ", loginUser='" + loginUser + '\'' +
                ", passwordUser='" + passwordUser + '\'' +
                ", emailUser='" + emailUser + '\'' +
                ", firstNameUser='" + firstNameUser + '\'' +
                ", lastNameUser='" + lastNameUser + '\'' +
                ", nameSchoolUser='" + nameSchoolUser + '\'' +
                ", userId='" + userId + '\'' +
                ", userRole='" + userRole + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getLoginUser(), user.getLoginUser()) && Objects.equals(getPasswordUser(), user.getPasswordUser()) && Objects.equals(emailUser, user.emailUser) && Objects.equals(getFirstNameUser(), user.getFirstNameUser()) && Objects.equals(getLastNameUser(), user.getLastNameUser()) && Objects.equals(nameSchoolUser, user.nameSchoolUser) && Objects.equals(getUserId(), user.getUserId()) && getUserRole() == user.getUserRole() && Objects.equals(getGrades(), user.getGrades());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLoginUser(), getPasswordUser(), emailUser, getFirstNameUser(), getLastNameUser(), nameSchoolUser, getUserId(), getUserRole(), getGrades());
    }
}
