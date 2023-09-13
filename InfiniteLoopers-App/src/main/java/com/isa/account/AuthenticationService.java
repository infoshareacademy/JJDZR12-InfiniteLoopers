package com.isa.account;

import java.util.List;

public class AuthenticationService {
    private UserManager userManager;
    public AuthenticationService (UserManager userManager) {
        this.userManager = userManager;
    }
    public User login(String login, String password) {
        List<User> userList = userManager.loadUsersFromFile();
        for (User user : userList) {
            if (user.getLoginUser().equals(login) && user.getPasswordUser().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
