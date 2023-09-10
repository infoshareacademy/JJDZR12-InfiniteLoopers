package com.isa.account;
//TODO zrobic logowanie razem z User
public class AdminUser extends User {
    public AdminUser () {
        setUserRole(UserRole.ADMINISTRATOR);
        setDefaultCredentials();
    }

    public  void  addRoleToUser (User user, UserRole role) {
        user.setUserRole(role);
    }

    private  void setDefaultCredentials () {
        setLoginUser("admin");
        setPasswordUser("admin");
    }

    public boolean authenficate(String enteredPassword) {
        return getPasswordUser().equals(enteredPassword);
    }
}
