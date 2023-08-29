package com.isa.menu;

import java.util.List;
import java.util.Scanner;
import static com.isa.menu.OptionService.backToMainMenu;


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

            for (Option x : optionList) {

                System.out.println(x.getIndex() + " " + x.getName());
            }
            System.out.println("\n");
            System.out.print("Wybierz opcję wprowadzając numer opcji i zatwierdź ją enterem: ");
            String option = scanner.nextLine();

            try {
                int value = Integer.parseInt(option);

                switch (value) {
                    case 1:
                        ClearConsole.clearConsole();
                        System.out.println("Przechodzi do logowania");
                        backToMainMenu();
                        break;

                    case 2:
                        ClearConsole.clearConsole();
                        System.out.println("Przechodzi do rejestracji");
                        backToMainMenu();
                        break;


                    case 3:
                        ClearConsole.clearConsole();
                        System.out.println("\n");
                        System.out.println("\n");
                        System.out.println("**************************************************");
                        System.out.println("     Program został zakończony...Do widzenia!     ");
                        System.out.println("**************************************************");
                        System.out.println("\n");
                        System.out.println("\n");
                        break;
                    default:
                        ClearConsole.clearConsole();
                        System.out.println("**************************************************");
                        System.out.println("Nieprawidłowy wybór opcji! Wybierz prawidłową opcję");
                        System.out.println("**************************************************");
                        menuInvoke();
                        break;
                }
            } catch (NumberFormatException e) {

                ClearConsole.clearConsole();
                System.out.println("**************************************************");
                System.out.println("Opcja nie może być literą! Wybierz opcję podając cyfrę");
                System.out.println("**************************************************");
                menuInvoke();

            }
        }

}

