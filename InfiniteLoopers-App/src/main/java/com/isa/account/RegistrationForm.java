package com.isa.account;

import java.util.List;

public class RegistrationForm {

    public void registrationFrom() {
        UserManager userManager = new UserManager();
        User user = new User();

        user.readUserInput();
        userManager.addUser(user);

        userManager.saveUsersToFile("users.json");

        List<User> loaderUsers = userManager.loadUsersFromFile("users.json");
        for (User user2 : loaderUsers) {
            System.out.println("Dodan uzytkownik " + user.getLoginNewUser());
        }


    }
}
