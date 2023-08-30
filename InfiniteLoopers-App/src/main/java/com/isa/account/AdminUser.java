package com.isa.account;

public class AdminUser extends User {
    public AdminUser () {
        setUserRole(UserRole.ADMINISTRATOR);
        setDefaultCredentials();
    }

    public  void  addRoleToUser (User user, UserRole role) {
        user.setUserRole(role);
    }

    private  void setDefaultCredentials () {
        setLoginNewUser("admin");
        setPasswordNewUser("admin");
    }

    public boolean authenficate(String enteredPassword) {
        return getPasswordNewUser().equals(enteredPassword);
    }
}
