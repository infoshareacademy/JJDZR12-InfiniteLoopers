package com.isa.account;

import com.isa.menu.ClearConsole;

import java.util.List;
import java.util.Scanner;

import static com.isa.menu.Menu.menuInvoke;
import static com.isa.menu.OptionService.backToMainMenu;
import static com.isa.menu.OptionService.closeApp;

public class LoginManager {
    public LoggedUser getLoggedUser() {
        return loggedUser;
    }

    private LoggedUser loggedUser = new LoggedUser();
    private static AuthenticationService authService;
    private static UserInputReader userInputReader;

    public LoginManager(AuthenticationService authService) {
        this.authService = authService;
        this.userInputReader = new UserInputReader();
    }

    public void login() {
        System.out.println("\n\nWitam. Proszę wprowadzić login i hasło.");

        String loginUser = userInputReader.readNonEmptyString("Login: ");
        String passwordUser = userInputReader.readNonEmptyString("Hasło: ");

        User user = authService.login(loginUser, passwordUser);


        if (user != null) {
            if (user.getUserRole() == null) {
                System.out.println("Twoje konto nie jest jeszcze aktywne. Spróbuj ponownie później lub skontaktuj się z administratorem.");
                menuInvoke();
            } else {
                loggedUser.logUser(user);
                System.out.println("Jesteś zalogowany :)!");
                RoleAssinger.waitingEnter();
            }
        } else {
            System.out.println("Niepoprawne dane! Spróbuj jeszcze raz!");
            RoleAssinger.waitingEnter();
            login();
        }
    }

    public void loginAdmin() {
        UserManager userManager = new UserManager();

        AdminUser admin = new AdminUser();
        userManager.addUser(admin);

        String enterLogin = userInputReader.readNonEmptyString("Wprowadź login administratora: ");
        String enteredPassword = userInputReader.readNonEmptyString("Wprowadź hasło administratora: ");

        if (admin.getLoginUser().equals(enterLogin) && admin.authenticate(enteredPassword)) {
            System.out.println("\nAdministrator zalogowany!\n");
            RoleAssinger.waitingEnter();
            RoleAssinger roleAssinger = new RoleAssinger();
            List<User> userList = userManager.loadUsersFromFile();
            if (userList.stream().filter(user -> user.getUserRole() == null).toArray().length != 0) {
                roleAssinger.assingRoles(userList, admin);
            } else {
                System.out.println("Wszyscy użytkownicy posiadają role. Powrót do głównego menu.");
                RoleAssinger.waitingEnter();
                menuInvoke();
            }

        } else {
            System.out.println("Administrator nie jest zalogowany.");
            System.out.println("1. Spubój jeszcze raz.");
            System.out.println("2. Powrot do menu glownego.");
            System.out.println("3. Zamknij aplikację.");
            System.out.println("\n");
            System.out.print("Wybierz opcję wprowadzając numer opcji i zatwierdź ją enterem: ");
            System.out.println("\n");
            choiceOfOption();

        }
    }

    public void choiceOfOption() {
        String value = userInputReader.readNonEmptyString("Podaj wartość: ");

        try {
            Integer choice = Integer.valueOf(value);

            switch (choice) {
                case 1 -> {
                    loginAdmin();
                }
                case 2 -> {
                    backToMainMenu();
                }
                case 3 -> {
                    closeApp();
                }
                default -> {
                    ClearConsole.clearConsole();
                    System.out.println("***************************************************");
                    System.out.println("Nieprawidłowy wybór opcji! Wybierz prawidłową opcję");
                    System.out.println("***************************************************");
                    System.out.println("\n");
                    RoleAssinger.waitingEnter();
                    loginAdmin();
                }
            }
        } catch (NumberFormatException e) {
            ClearConsole.clearConsole();
            System.out.println("******************************************************");
            System.out.println("Opcja nie może być literą! Wybierz opcję podając cyfrę");
            System.out.println("******************************************************");
            System.out.println("\n");
            RoleAssinger.waitingEnter();
            loginAdmin();
        }
    }
}
