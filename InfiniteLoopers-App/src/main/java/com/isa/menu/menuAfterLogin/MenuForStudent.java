package com.isa.menu.menuAfterLogin;

import com.isa.menu.ClearConsole;

import java.util.Scanner;

import static com.isa.menu.Menu.menuInvoke;
import static com.isa.menu.OptionService.backToMainMenu;
import static com.isa.menu.OptionService.closeApp;

public class MenuForStudent {
    public static void menuForTeacher() {
        System.out.println("\n");
        System.out.println("**************************************************");
        System.out.println("          Witaj Uczniu! Co chcesz zrobic?         ");
        System.out.println("**************************************************");
        System.out.println("\n");
        System.out.println("1. Wyswietl swoje oceny.");
        System.out.println("2. Powrot do menu glownego.");
        System.out.println("3. Zamknij aplikacje.");
        System.out.println("\n");
        System.out.print("Wybierz opcję wprowadzając numer opcji i zatwierdź ją enterem: ");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();

        try {
            int choice = Integer.parseInt(value);

            switch (choice) {
                case 1 ->{
                    ClearConsole.clearConsole();
                    menuInvoke();
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
                    scanner.next();
                }
            }
        } catch (NumberFormatException e) {
            ClearConsole.clearConsole();
            System.out.println("******************************************************");
            System.out.println("Opcja nie może być literą! Wybierz opcję podając cyfrę");
            System.out.println("---------------Powrót do menu głównego----------------");
            System.out.println("******************************************************");
            scanner.next();
        }
    }
}
