package com.isa.account;

import java.util.List;

public class RegistrationForm {
    private UserInputReader userInputReader;
    private UserManager userManager;

    public RegistrationForm (UserInputReader userInputReader, UserManager userManager) {
        this.userInputReader = userInputReader;
        this.userManager = userManager;
    }

    public void registrationFrom() {
        UserManager userManager = new UserManager();
        User user = new User();

        AdminUser admin = new AdminUser();
        userManager.addUser(admin);

        user.readUserInput();
        userManager.addUser(user);

        userManager.saveUsersToFile("users.json");

        String enterLogin = userInputReader.readNonEmptyString("Wprowadz login administratora: ");
        String enteredPassword = userInputReader.readNonEmptyString("Wprowadz haslo administratora: ");

        if (admin.getLoginNewUser().equals(enterLogin) && admin.authenficate(enteredPassword)) {
            System.out.println("Administrator zalogowany!");

            RoleAssinger roleAssinger = new RoleAssinger();
            roleAssinger.assingRoles(userManager.getUserList().getUsers(), admin);
        } else {
            System.out.println("Administrator nie jest zalogowany");
        }

        userManager.saveUsersToFile("users.json");

        List<User> loaderUsers = userManager.loadUsersFromFile("users.json");
        for (User user2 : loaderUsers) {
            System.out.println("Dodan uzytkownik " + user2.getLoginNewUser());
        }


    }
}
