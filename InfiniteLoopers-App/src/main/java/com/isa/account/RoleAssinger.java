package com.isa.account;

import java.util.List;

public class RoleAssinger {
    private UserInputReader userInputReader = new UserInputReader();

    public void assingRoles(List<User> userList, AdminUser admin) {
        System.out.println("User: ");
        for (User user : userList) {
            System.out.println(user.getLoginUser());
        }

        String selectUserLogin = userInputReader.readNonEmptyString("Wprowadz login do ktorego chcesz przepisac role: ");

        User selectedUser = null;
        for (User user : userList) {
            if (user.getLoginUser().equals(selectUserLogin)) {
                selectedUser = user;
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
            }
        } else {
            System.out.println("User nie zostal znaleziony");
        }
    }
}

