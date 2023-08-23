package com.isa.account;

public class MainTestClass {
    public static void main(String[] args) {
        User user = new User();
        user.readUserInput();

        System.out.println("Dane nowego użytkownika:");
        System.out.println("Login: " + user.getLoginNewUser());
        System.out.println("Hasło: " + user.getPasswordNewUser());
        System.out.println("E-mail: " + user.getEmailNewUser());
    }
}
