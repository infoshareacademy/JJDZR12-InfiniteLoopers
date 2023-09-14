package com.isa.account;

import java.io.IOException;
import java.util.List;

public class AdminUser extends User {
    UserManager userManager = new UserManager();
    public AdminUser () {
        setUserRole(UserRole.ADMINISTRATOR);
        setDefaultCredentials();
    }
    public  void  addRoleToUser (User user, UserRole role, List<User> userList) throws IOException {
        userList.stream().filter(s -> s.getUserId().equals(user.getUserId())).findFirst().orElseThrow().setUserRole(role);
        userManager.updateUser(userList);
    }
    private  void setDefaultCredentials () {
        setLoginUser("admin");
        setPasswordUser("admin");
        setUserId("null");
    }
    public boolean authenticate(String enteredPassword) {
        return getPasswordUser().equals(enteredPassword);
    }
}
