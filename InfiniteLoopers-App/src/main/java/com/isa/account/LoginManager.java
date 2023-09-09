package com.isa.account;

public class LoginManager {
    private AuthenticationService authService;
    private UserInputReader userInputReader;

    public LoginManager(AuthenticationService authService) {
        this.authService = authService;
        this.userInputReader = new UserInputReader();
    }

    public void login () {
        System.out.println("Witam. Prosze wprowadzic login i haslo.");

        String userName = userInputReader.readNonEmptyString("Login: ");
        String passwordUser = userInputReader.readNonEmptyString("Haslo: ");

        User user = authService.login(userName, passwordUser);

        if (user != null) {
            System.out.println("Jestes zalogowany");
        } else {
            System.out.println("Niepoprawne dane! Sprobuj jeszcze raz!");
            login();
        }
    }
}
