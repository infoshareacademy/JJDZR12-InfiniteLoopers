package com.isa.account;

import java.util.List;

public class AuthenticationService {
    private UserManager userManager;
    public AuthenticationService (UserManager userManager) {
        this.userManager = userManager;
    }
    public User login(String userName, String password) {
        List<User> userList = userManager.loadUsersFromFile("users.json");
        for (User user : userList) {
            if (user.getLoginUser().equals(userName) && user.getPasswordUser().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
