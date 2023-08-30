package com.isa.account;

import java.util.List;

public class RoleAssinger {
    private UserInputReader userInputReader = new UserInputReader();

    public void assingRoles(List<User> userList, AdminUser admin) {
        System.out.println("User: ");
        for (User user : userList) {
            System.out.println(user.getAgeNewUser());
        }

        String selectUserLogin = userInputReader.readNonEmptyString("Wprowadz login do ktorego chcesz przepisac role: ");

        User selectedUser = null;
        for (User user : userList) {
            if (user.getLoginNewUser().equals(selectUserLogin)) {
                selectedUser = user;
                break;
            }
        }

        if (selectedUser != null) {
            System.out.println("Dodana rola: ");
            for (UserRole role : UserRole.values()) {
                System.out.println(role);
            }

            String selectedRoleStr = userInputReader.readNonEmptyString("Wprowadz role user: ");

            try {
                UserRole selectedRole = UserRole.valueOf(selectedRoleStr);
                admin.addRoleToUser(selectedUser, selectedRole);
                System.out.println("Rola dodana!");
            } catch (IllegalArgumentException error) {
                System.out.println("Blad w prowadzeniu roli.");
            }
        } else {
            System.out.println("User nie zostal znaleziony");
        }
    }
}

