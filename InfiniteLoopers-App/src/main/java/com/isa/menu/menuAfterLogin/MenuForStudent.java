package com.isa.menu.menuAfterLogin;

import com.isa.account.User;
import com.isa.account.UserInputReader;
import com.isa.account.UserManager;
import com.isa.menu.ClearConsole;

import java.util.List;

import static com.isa.menu.OptionService.backToMainMenu;
import static com.isa.menu.OptionService.closeApp;

public class MenuForStudent {
    public static void menuForStudent(User student) {

        UserManager userManager = new UserManager();
        List<User> userList = userManager.loadUsersFromFile();
        UserInputReader userInputReader = new UserInputReader();

        System.out.println("\n");
        System.out.println("**************************************************");
        System.out.println("          Uczniu, co chcesz zrobic?         ");
        System.out.println("**************************************************");
        System.out.println("\n");
        System.out.println("1. Wyswietl swoje oceny.");
        System.out.println("2. Powrot do menu glownego.");
        System.out.println("3. Zamknij aplikacje.");
        System.out.println("\n");
        System.out.print("Wybierz opcję wprowadzając numer opcji i zatwierdź ją enterem: ");
        System.out.println("\n");
        String value = userInputReader.readNonEmptyString("podaj wartosc: ");;

        try {
            Integer choice = Integer.valueOf(value);

            switch (choice) {
                case 1 ->{
                    System.out.println("Twoje oceny: ");
                    System.out.println("\n");
                    System.out.println(userList
                            .stream()
                            .filter(user -> user.getLoginUser().equals(student.getLoginUser()))
                            .findFirst()
                            .orElseThrow()
                            .getGrades()
                            .toString());
                    menuForStudent(student);
                }

                case 2 ->  {
                    backToMainMenu();
                }
                case 3 ->  {
                    closeApp();
                }

                default -> {
                    ClearConsole.clearConsole();
                    System.out.println("***************************************************");
                    System.out.println("Nieprawidłowy wybór opcji! Wybierz prawidłową opcję");
                    System.out.println("***************************************************");
                    System.out.println("\n");
                    menuForStudent(student);
                }
            }
        } catch (NumberFormatException e) {
            ClearConsole.clearConsole();
            System.out.println("******************************************************");
            System.out.println("Opcja nie może być literą! Wybierz opcję podając cyfrę");
            System.out.println("******************************************************");
            System.out.println("\n");
            menuForStudent(student);
        }
    }
}
