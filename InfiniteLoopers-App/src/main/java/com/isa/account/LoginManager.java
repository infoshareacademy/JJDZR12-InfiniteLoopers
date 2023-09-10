package com.isa.account;

public class LoginManager {
    private AuthenticationService authService;
    private UserInputReader userInputReader;

    public LoginManager(AuthenticationService authService) {
        this.authService = authService;
        this.userInputReader = new UserInputReader();
    }

    public void login () { //TODO zapytac czy ma byc void
        System.out.println("Witam. Prosze wprowadzic login i haslo.");

        String userName = userInputReader.readNonEmptyString("Login: ");
        String passwordUser = userInputReader.readNonEmptyString("Haslo: ");

        User user = authService.login(userName, passwordUser);

        if (user != null) {
            new LoggedUser().logUser(user);
            System.out.println("Jestes zalogowany");
            //TODO weryfikacja jaka jest przrpisana rola i w zaleznosci od tego uruchomic menu
            //TODO przepisac usedID do
        } else {
            System.out.println("Niepoprawne dane! Sprobuj jeszcze raz!");
            login();
        }
    }
}
