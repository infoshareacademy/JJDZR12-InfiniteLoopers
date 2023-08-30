package com.isa.account;

public class AdminUser extends User {
    public AdminUser () {
        setUserRole(UserRole.ADMINISTRATOR);
    }

    public  void  addRoleToUser (User user, UserRole role) {
        user.setUserRole(role);
    }
}
