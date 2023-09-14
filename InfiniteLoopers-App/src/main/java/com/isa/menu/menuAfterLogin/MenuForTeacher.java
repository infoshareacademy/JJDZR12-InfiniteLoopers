package com.isa.menu.menuAfterLogin;

import com.isa.account.User;
import com.isa.account.UserInputReader;
import com.isa.account.UserManager;
import com.isa.account.UserRole;
import com.isa.subjects.Subjects;
import com.isa.menu.ClearConsole;

import java.io.IOException;
import java.util.*;

import static com.isa.menu.OptionService.backToMainMenu;
import static com.isa.menu.OptionService.closeApp;

public class MenuForTeacher  {


    public static void menuForTeacher() {

        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        UserInputReader userInputReader = new UserInputReader();
        List<User> userList = userManager.loadUsersFromFile();

            System.out.println("\n");
            System.out.println("**************************************************");
            System.out.println("      Nauczycielu! Co chcesz zrobic?        ");
            System.out.println("**************************************************");
            System.out.println("\n");
            System.out.println("1. Wyswietl oceny ucznia.");
            System.out.println("2. Dodaj oceny ucznia.");
            System.out.println("3. Powrot do menu glownego.");
            System.out.println("4. Zamknij aplikacje.");
            System.out.println("\n");
            System.out.print("Wybierz opcję wprowadzając numer opcji i zatwierdź ją enterem: ");

            String value = userInputReader.readNonEmptyString("Podaj numer opcji: ");;
            try {
                Integer choice = Integer.valueOf(value);

                switch (choice) {
                    case 1 ->{
                        String selectUserLogin = null;
                        String finalSelectUserLogin;
                        boolean isLoginMatching = false;

                        System.out.println("Uczniowie: ");
                        userList.stream().filter(user -> !user.getUserId().equals("null")).filter(user -> user.getUserRole() == UserRole.STUDENT).map(user -> "Login: " + user.getLoginUser() + ", Imie i nazwisko: " + user.getFirstNameUser() + " " + user.getLastNameUser()).forEach(System.out::println);

                        while (!isLoginMatching) {
                            System.out.println("Podaj login ucznia dla ktorego chcesz wyswietlic oceny: ");
                            selectUserLogin = scanner.nextLine();
                            for (int i = 0; i < userList.size(); i++) {
                                if (userList.get(i).getLoginUser().equals(selectUserLogin)) {
                                    isLoginMatching = true;
                                    break;
                                };
                            }

                        }
                        finalSelectUserLogin = selectUserLogin;

                        if (isLoginMatching){

                            userList
                                    .stream()
                                    .filter(userFromList -> userFromList.getLoginUser().equals(finalSelectUserLogin))
                                    .findFirst()
                                    .ifPresentOrElse(user -> {
                                        System.out.println("Oceny ucznia: " + user.getFirstNameUser() + " " + user.getLastNameUser() );
                                        System.out.println("\n");
                                        System.out.println(user.getGrades().toString());
                                    }, () -> {
                                        throw new NoSuchElementException("Zly login");
                                    });


                        }
                        menuForTeacher();
                    }
                    case 2 ->  {
                        String selectUserLogin = null;
                        String finalSelectUserLogin;
                        boolean isLoginMatching = false;
                        Subjects selectUserSubject = null;
                        Subjects finalSelectUserSubject;
                        Integer selectUserGrade = null;
                        boolean isSubjectMatching = false;
                        boolean isGradeValid = false;

                        System.out.println("Uczniowie: ");
                        userList.stream().filter(user -> !user.getUserId().equals("null")).filter(user -> user.getUserRole() == UserRole.STUDENT).map(user -> "Login: " + user.getLoginUser() + ", Imie i nazwisko: " + user.getFirstNameUser() + " " + user.getLastNameUser()).forEach(System.out::println);

                        while (!isLoginMatching) {
                            selectUserLogin = userInputReader.readNonEmptyString("Podaj login ucznia dla ktorego chcesz dodac ocene: ");;
                            for (int i = 0; i < userList.size(); i++) {
                                if (userList.get(i).getLoginUser().equals(selectUserLogin)) {
                                    isLoginMatching = true;
                                    break;
                                }
                            }
                        }
                        finalSelectUserLogin = selectUserLogin;

                        System.out.println("Przedmioty: ");
                        System.out.println(Arrays.toString(Subjects.values()));

                        while (!isSubjectMatching) {
                            try {
                                selectUserSubject = Subjects.valueOf(userInputReader.readNonEmptyString("Wprowadz przedmiot: ").toUpperCase());
                            } catch (IllegalArgumentException error){
                                System.out.println("Podaj poprawny przedmiot");
                            }
                            for (int i = 0; i < Subjects.values().length; i++) {
                                if (Arrays.stream(Subjects.values()).toArray()[i] == selectUserSubject) {
                                    isSubjectMatching = true;
                                    break;
                                }
                            }

                        }
                        finalSelectUserSubject = selectUserSubject;
                        System.out.println("Podaj ocene: ");

                        while (!isGradeValid) {
                            String scannedValue = scanner.nextLine();
                            try {
                                selectUserGrade = Integer.parseInt(scannedValue);
                                if (selectUserGrade < 7 && selectUserGrade > 0) {
                                    isGradeValid = true;
                                } else {
                                    System.out.println("Ocena musi byc w przedzial od 1 do 6");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Podaj poprawna liczbe: ");
                            }
                            catch (InputMismatchException e) {
                                System.out.println("Podaj poprawna liczbe: ");
                            }
                        }
                        userList.stream().filter(user -> user.getLoginUser().equals(finalSelectUserLogin)).findFirst().orElseThrow().getGrades().get(finalSelectUserSubject).add(selectUserGrade);
                        userManager.updateUser(userList);
                        menuForTeacher();
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
                        System.out.println("\n");
                        scanner.next();
                    }
                }
            } catch (NumberFormatException | IOException e) {

                System.out.println("******************************************************");
                System.out.println("Opcja nie może być literą! Wybierz opcję podając cyfrę");
                System.out.println("******************************************************");
                System.out.println("\n");
                menuForTeacher();
            }
        }
}
