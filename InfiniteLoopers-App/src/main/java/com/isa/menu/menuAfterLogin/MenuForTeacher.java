package com.isa.menu.menuAfterLogin;

import com.isa.account.User;
import com.isa.account.UserInputReader;
import com.isa.account.UserManager;
import com.isa.account.UserRole;
import com.isa.grades.GradesManagement;
import com.isa.grades.Subjects;
import com.isa.menu.ClearConsole;

import java.io.IOException;
import java.util.*;

import static com.isa.menu.Menu.menuInvoke;
import static com.isa.menu.OptionService.backToMainMenu;
import static com.isa.menu.OptionService.closeApp;

public class MenuForTeacher  {


    public static void menuForTeacher() {


            System.out.println("\n");
            System.out.println("**************************************************");
            System.out.println("      Witaj Nauczycielu! Co chcesz zrobic?        ");
            System.out.println("**************************************************");
            System.out.println("\n");
            System.out.println("1. Wyswietl oceny ucznia.");
            System.out.println("2. Dodaj oceny ucznia.");
            System.out.println("3. Powrot do menu glownego.");
            System.out.println("4. Zamknij aplikacje.");
            System.out.println("\n");
            System.out.print("Wybierz opcję wprowadzając numer opcji i zatwierdź ją enterem: ");
            Scanner scanner = new Scanner(System.in);
            String value = scanner.nextLine();

            try {
                int choice = Integer.parseInt(value);

                switch (choice) {
                    case 1 ->{
                        UserInputReader userInputReader = new UserInputReader();
                        List<User> userList = new UserManager().loadUsersFromFile();
                        System.out.println("Students: ");
                        userList.stream().filter(user -> !user.getUserId().equals("null")).filter(user -> user.getUserRole().equals(UserRole.STUDENT)).map(User::getLoginUser).forEach(System.out::println);
                        String selectUserLogin = userInputReader.readNonEmptyString("Podaj ucznia dla ktorego chcesz wyswietlic oceny ");
                        System.out.println(userList.stream().filter(user -> user.getLoginUser().equals(selectUserLogin)).findFirst().orElseThrow().getGrades().toString());


                    }
                    case 2 ->  {
                        UserInputReader userInputReader = new UserInputReader();
                        List<User> userList = new UserManager().loadUsersFromFile();
                        System.out.println("Students: ");
                        userList.stream().filter(user -> !user.getUserId().equals("null")).filter(user -> user.getUserRole().equals(UserRole.STUDENT)).map(User::getLoginUser).forEach(System.out::println);
                        String selectUserLogin = userInputReader.readNonEmptyString("Podaj ucznia dla ktorego chcesz dodac ocene ");
                        String selectUserId = userList.stream().filter(user -> user.getLoginUser().equals(selectUserLogin)).findFirst().get().getUserId();
                        System.out.println(selectUserId);
                        System.out.println("Subjects: ");
                        System.out.println(Arrays.toString(Subjects.values()));
                        Subjects selectUserSubject= Subjects.valueOf(userInputReader.readNonEmptyString("Podaj przedmiot dla ktorego chcesz dodac ocene "));
                        System.out.println("podaj ocene");
                        Integer selectUserGrade = scanner.nextInt();


                        userList.stream().filter(user -> user.getLoginUser().equals(selectUserLogin)).findFirst().orElseThrow().getGrades().get(selectUserSubject).add(selectUserGrade);
                        UserManager userManager = new UserManager();
                        userManager.updateUser(userList);


                    }
                    case 3 ->  {
                        backToMainMenu();
                    }
                    case 4 ->  {
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
            } catch (NumberFormatException | IOException e) {
                ClearConsole.clearConsole();
                System.out.println("******************************************************");
                System.out.println("Opcja nie może być literą! Wybierz opcję podając cyfrę");
                System.out.println("---------------Powrót do menu głównego----------------");
                System.out.println("******************************************************");
                scanner.next();
            }
        }
}
