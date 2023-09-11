package com.isa.menu;


import com.isa.account.*;

import java.util.List;
import java.util.Scanner;

import static com.isa.menu.OptionService.closeApp;
import static com.isa.menu.menuAfterLogin.MenuForStudent.menuForStudent;
import static com.isa.menu.menuAfterLogin.MenuForTeacher.menuForTeacher;


public class Menu {

    private Menu() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
        public static void menuInvoke() {

            Scanner scanner = new Scanner(System.in);
            List<Option> optionList = OptionService.getOptionList();


            System.out.println("**************************************************");
            System.out.println("                Dziennik XXXXXXXX                 ");
            System.out.println("**************************************************");
            System.out.println("\n");

            for (Option option : optionList) {
                System.out.println(option.getIndex() + " " + option.getName());
            }
            System.out.println("\n");
            System.out.print("Wybierz opcję wprowadzając numer opcji i zatwierdź ją enterem: ");
            String option = scanner.nextLine();

            try {
                int value = Integer.parseInt(option);

                switch (value) {
                    case 1 -> {

                        UserManager userManager = new UserManager();
                        AuthenticationService authService = new AuthenticationService(userManager);
                        LoginManager loginManager = new LoginManager(authService);
                        loginManager.login();
                        UserRole userRole = loginManager.getLoggedUser().getUser().getUserRole();
                        System.out.println(userRole);

                        try {
                            if (userRole.equals(UserRole.STUDENT)) {
                                menuForStudent();
                            } else {
                                menuForTeacher();
                            }
                        } catch (NullPointerException e){
                            System.out.println("Poczekaj na potwierdzenie rejestracji i nadanie roli.");
                            menuInvoke();
                        }




                    }

                    case 2 -> {

                        UserInputReader userInputReader = new UserInputReader();
                        UserManager userManager = new UserManager();
                        RegistrationForm registrationForm = new RegistrationForm(userInputReader, userManager);
                        registrationForm.registrationFrom();
                        menuInvoke();

                    }

                    case 3 -> {
                            closeApp();
                        }

                    case 4 -> {

                        UserManager userManager = new UserManager();
                        AuthenticationService authService = new AuthenticationService(userManager);
                        LoginManager loginManager = new LoginManager(authService);

                        loginManager.loginAdmin();
                        menuInvoke();


                    }
                    default -> {
                        ClearConsole.clearConsole();
                        System.out.println("**************************************************");
                        System.out.println("Nieprawidłowy wybór opcji! Wybierz prawidłową opcję");
                        System.out.println("**************************************************");
                        menuInvoke();
                    }
                }
            } catch (NumberFormatException e) {
                ClearConsole.clearConsole();
                System.out.println("******************************************************");
                System.out.println("Opcja nie może być literą! Wybierz opcję podając cyfrę");
                System.out.println("******************************************************");
                menuInvoke();
            }
        }

}

