package com.isa.account;

import java.util.List;


public class AdminUser extends User {
    UserManager userManager = new UserManager();
    private static final String fileName = "users.json";
    public AdminUser () {
        setUserRole(UserRole.ADMINISTRATOR);
        setDefaultCredentials();
    }

    public  void  addRoleToUser (User user, UserRole role, List<User> userList) {
     userList.stream().filter(s -> s.getUserId().equals(user.getUserId())).findFirst().get().setUserRole(role);
        userManager.saveUsersToFile(fileName);

    }

    private  void setDefaultCredentials () {
        setLoginUser("admin");
        setPasswordUser("admin");
    }

    public boolean authenficate(String enteredPassword) {
        return getPasswordUser().equals(enteredPassword);
    }
}
