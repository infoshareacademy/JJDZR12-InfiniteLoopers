package com.isa.account;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RoleAssinger {
    private UserInputReader userInputReader = new UserInputReader();

    public void assingRoles(List<User> userList, AdminUser admin) {
        RoleAssinger roleAssinger = new RoleAssinger();
        UserRole selectedRole = null;
        System.out.println("Użytkownicy którym trzeba przypisać role: \n__________________________________________");
        userList.stream()
                .filter(user -> !user.getUserId().equals("null"))
                .filter(user -> user.getUserRole() == null).map(user -> "Login: " + user.getLoginUser() + ", Imie i nazwisko: " + user.getFirstNameUser() + " " + user.getLastNameUser()).forEach(System.out::println);

        String selectUserLogin = userInputReader.readNonEmptyString("__________________________________________\nWprowadź login do którego chcesz przepisać role: ");

        User selectedUser = null;
        for (User user : userList) {
            if (user.getLoginUser().equals(selectUserLogin)) {
                selectedUser = user;
                System.out.println("Wybrany uzytkownik: " + selectedUser.getFirstNameUser() + " " +selectedUser.getLastNameUser() + "\n*");
                break;
            }
        }
        if (selectedUser != null) {
            System.out.println("ROLE: ");
            Arrays.stream(UserRole.values())
                    .filter(value -> !value.equals(UserRole.ADMINISTRATOR))
                    .forEach(System.out::println);

            do {
                try {
                    String selectedRoleStr = userInputReader.readNonEmptyString("*\nWprowadź role użytkownika: ").toUpperCase();
                    selectedRole = UserRole.valueOf(selectedRoleStr);
                    admin.addRoleToUser(selectedUser, selectedRole, userList);
                    System.out.println("*\nRola dodana!\n*");
                    waitingEnter();
                } catch (IllegalArgumentException error) {
                    System.out.println("Podaj poprawna role.");
                    waitingEnter();
                    System.out.println("ROLE: ");
                    Arrays.stream(UserRole.values()).filter(value -> !value.equals(UserRole.ADMINISTRATOR)).forEach(System.out::println);

                } catch (IOException e) {
                    System.out.println("Blad wczytywania pliku.");
                }
            } while (selectedRole == null);
        } else {
            System.out.println("User nie zostal znaleziony");
            waitingEnter();
            roleAssinger.assingRoles(userList, admin);
        }
    }

    public static void waitingEnter () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Naciśnij Enter, aby kontynuować...");
        scanner.nextLine();
    }
}

