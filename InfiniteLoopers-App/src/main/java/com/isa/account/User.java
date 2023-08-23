package com.isa.account;

public class User {
    private UserInputReader userInputReader = new UserInputReader();
    private String loginNewUser;
    private String passwordNewUser;
    private String emailNewUser;
    private String firstNameNewUser;
    private String lastNameNewUser;
    private int ageNewUser;
    private String nameSchoolNewUser;
    private String userNewId;
    private UserRole userRole;

    public User () {}

    public void readUserInput() {
        System.out.println("Rejestracja nowego użytkownika");
        this.loginNewUser = userInputReader.readNonEmptyString("Wprowadz Login: ");
        this.passwordNewUser = userInputReader.readNonEmptyString("Wprowadz haslo: ");

        do {
            this.emailNewUser = userInputReader.readNonEmptyString("Wprowadz adres e-mail: ");
            if (!this.emailNewUser.contains("@")) {
                System.out.println("Adres email ma zawierac @. Prosze wprowadzic poprawny email");
            }
        } while (!this.emailNewUser.contains("@"));

        this.firstNameNewUser = userInputReader.readNonEmptyString("Wprowadz Imie: ");
        this.lastNameNewUser = userInputReader.readNonEmptyString("Wporawdz Nazwisko: ");
        int ageNewUser;

        do {
            ageNewUser = Integer.parseInt(userInputReader.readNonEmptyString("Wprowadz wiek: "));
            if (ageNewUser < 6 || ageNewUser > 99) {
                System.out.println("Wiek ma byc w przedzialie od 7 do 99");
            }
        } while (ageNewUser < 6 || ageNewUser > 99);

        this.nameSchoolNewUser = userInputReader.readNonEmptyString("Wporawdz nazwe szkoly: ");
        this.userNewId = IdGenerator.generateUniqueId();
    }

    public String getLoginNewUser() {
        return loginNewUser;
    }

    public void setLoginNewUser(String loginNewUser) {
        this.loginNewUser = loginNewUser;
    }

    public String getPasswordNewUser() {
        return passwordNewUser;
    }

    public void setPasswordNewUser(String passwordNewUser) {
        this.passwordNewUser = passwordNewUser;
    }

    public String getEmailNewUser() {
        return emailNewUser;
    }

    public void setEmailNewUser(String emailNewUser) {
        this.emailNewUser = emailNewUser;
    }

    public String getFirstNameNewUser() {
        return firstNameNewUser;
    }

    public void setFirstNameNewUser(String firstNameNewUser) {
        this.firstNameNewUser = firstNameNewUser;
    }

    public String getLastNameNewUser() {
        return lastNameNewUser;
    }

    public void setLastNameNewUser(String lastNameNewUser) {
        this.lastNameNewUser = lastNameNewUser;
    }

    public int getAgeNewUser() {
        return ageNewUser;
    }

    public void setAgeNewUser(int ageNewUser) {
        this.ageNewUser = ageNewUser;
    }

    public String getNameSchoolNewUser() {
        return nameSchoolNewUser;
    }

    public void setNameSchoolNewUser(String nameSchoolNewUser) {
        this.nameSchoolNewUser = nameSchoolNewUser;
    }

    public String getUserNewId() {
        return userNewId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "userInputReader=" + userInputReader +
                ", loginNewUser='" + loginNewUser + '\'' +
                ", passwordNewUser='" + passwordNewUser + '\'' +
                ", emailNewUser='" + emailNewUser + '\'' +
                ", firstNameNewUser='" + firstNameNewUser + '\'' +
                ", lastNameNewUser='" + lastNameNewUser + '\'' +
                ", ageNewUser=" + ageNewUser +
                ", nameSchoolNewUser='" + nameSchoolNewUser + '\'' +
                ", userNewId='" + userNewId + '\'' +
                ", userRole='" + userRole + '\'' +
                "}\n";
    }
}
