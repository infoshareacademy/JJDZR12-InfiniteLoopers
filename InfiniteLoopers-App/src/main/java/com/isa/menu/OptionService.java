package com.isa.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static com.isa.menu.Menu.menuInvoke;

public class OptionService {

    private OptionService() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    protected static final List<Option> optionList = createOptionList();

    public static List <Option> createOptionList() {

        List <Option> optionList = new ArrayList<>();

        Option optionLogin = new Option(1,"Zaloguj sie");
        optionList.add(optionLogin);

        Option optionLogOut = new Option(2,"Zarejestruj sie");
        optionList.add(optionLogOut);

        Option optionQuit = new Option(3,"Zakończ program");
        optionList.add(optionQuit);

        Option optionLoginAdmin = new Option(4,"Zloguj administratora");
        optionList.add(optionLoginAdmin);

        return optionList;

    }

    public static List<Option> getOptionList() {
        return optionList;
    }

    public static void backToMainMenu() {
        System.out.println("\n");
        System.out.println("**************************************************");
        System.out.println("            Czy wrócić do menu głównego?          ");
        System.out.println("**************************************************");
        System.out.println("\n");
        System.out.println("1. Tak");
        System.out.println("2. Nie, powróć do głównego menu");
        System.out.println("\n");
        System.out.print("Wybierz opcję wprowadzając numer opcji i zatwierdź ją enterem: ");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();

        try {
            int choice = Integer.parseInt(value);

            switch (choice) {
                case 1:
                    ClearConsole.clearConsole();
                    menuInvoke();
                    default:
                    ClearConsole.clearConsole();
                    System.out.println("***************************************************");
                    System.out.println("Nieprawidłowy wybór opcji! Wybierz prawidłową opcję");
                    System.out.println("------------Powrót do menu głównego----------------");
                    System.out.println("***************************************************");
                    menuInvoke();
                    break;
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

    public static void closeApp(){
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("**************************************************");
        System.out.println("     Program został zakończony...Do widzenia!     ");
        System.out.println("**************************************************");
        System.out.println("\n");
        System.out.println("\n");
    }



}
