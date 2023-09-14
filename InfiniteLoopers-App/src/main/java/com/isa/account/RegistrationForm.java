package com.isa.account;

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

        user.readUserInput();
        userManager.addUser(user);

        userManager.saveUsersToFile();
    }
}
