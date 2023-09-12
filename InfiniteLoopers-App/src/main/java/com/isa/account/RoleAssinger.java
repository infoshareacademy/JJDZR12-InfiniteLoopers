package com.isa.account;

import java.io.IOException;
import java.util.List;

public class RoleAssinger {
    private UserInputReader userInputReader = new UserInputReader();

    public void assingRoles(List<User> userList, AdminUser admin) {
        System.out.println("Users: ");
        userList.stream().filter(user -> !user.getUserId().equals("null")).filter(user -> user.getUserRole() == null).map(User::getLoginUser).forEach(System.out::println);

        String selectUserLogin = userInputReader.readNonEmptyString("Wprowadz login do ktorego chcesz przepisac role: ");

        User selectedUser = null;
        for (User user : userList) {
            if (user.getLoginUser().equals(selectUserLogin)) {
                selectedUser = user;
                System.out.println(selectedUser);
                break;
            }
        }

        if (selectedUser != null) {
            System.out.println("ROLE: ");
            for (UserRole role : UserRole.values()) {
                System.out.println(role);
            }

            String selectedRoleStr = userInputReader.readNonEmptyString("Wprowadz role uzytkownika: ");

            try {
                UserRole selectedRole = UserRole.valueOf(selectedRoleStr);
                admin.addRoleToUser(selectedUser, selectedRole, userList);
                System.out.println("Rola dodana!");
            } catch (IllegalArgumentException error) {
                System.out.println("Blad w prowadzeniu roli.");
            } catch (IOException e) {
                System.out.println("Blad wczytywania pliku.");
            }
        } else {
            System.out.println("User nie zostal znaleziony");
        }
    }
}

