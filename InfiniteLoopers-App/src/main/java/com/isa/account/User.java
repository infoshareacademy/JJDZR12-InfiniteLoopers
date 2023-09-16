package com.isa.account;

import com.isa.subjects.Subjects;

import java.util.*;

public class User {
    private UserInputReader userInputReader = new UserInputReader();
    private String loginUser;
    private String passwordUser;
    private String emailUser;
    private String firstNameUser;
    private String lastNameUser;
    private int ageUser;
    private String nameSchoolUser;
    private String userId;
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

    private UserRole userRole;

    public User () {
        this.grades = new HashMap<>();
        grades.put(Subjects.POLSKI,new ArrayList<>());
        grades.put(Subjects.WF,new ArrayList<>());
        grades.put(Subjects.BIOLOGIA,new ArrayList<>());
        grades.put(Subjects.MATEMATYKA,new ArrayList<>());
        grades.put(Subjects.ANGIELSKI,new ArrayList<>());
        grades.put(Subjects.SZTUKA,new ArrayList<>());
    }
    public void readUserInput() {
        System.out.println("Rejestracja nowego użytkownika");
        this.loginUser = userInputReader.readNonEmptyString("Wprowadz Login: ");
        this.passwordUser = userInputReader.readNonEmptyString("Wprowadz haslo: ");

        do {
            this.emailUser = userInputReader.readNonEmptyString("Wprowadz adres e-mail: ");
            if (!this.emailUser.contains("@")) {
                System.out.println("Adres email ma zawierac @. Prosze wprowadzic poprawny email");
            }
        } while (!this.emailUser.contains("@"));

        this.firstNameUser = userInputReader.readNonEmptyString("Wprowadz Imie: ");
        this.lastNameUser = userInputReader.readNonEmptyString("Wporawdz Nazwisko: ");
        int ageUser;

        do {
            ageUser = Integer.parseInt(userInputReader.readNonEmptyString("Wprowadz wiek: "));
            if (ageUser < 6 || ageUser > 99) {
                System.out.println("Wiek ma byc w przedzialie od 7 do 99");
            }
        } while (ageUser < 6 || ageUser > 99);

        this.nameSchoolUser = userInputReader.readNonEmptyString("Wporawdz nazwe szkoly: ");
        this.userId = IdGenerator.getUniqueId();
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
                "userInputReader=" + userInputReader +
                ", loginUser='" + loginUser + '\'' +
                ", passwordUser='" + passwordUser + '\'' +
                ", emailUser='" + emailUser + '\'' +
                ", firstNameUser='" + firstNameUser + '\'' +
                ", lastNameUser='" + lastNameUser + '\'' +
                ", ageUser=" + ageUser +
                ", nameSchoolUser='" + nameSchoolUser + '\'' +
                ", userId='" + userId + '\'' +
                ", userRole='" + userRole + '\'' +
                "}\n";
    }
}
