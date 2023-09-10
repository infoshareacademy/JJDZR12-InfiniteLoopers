package com.isa.account;

public class LoggedUser {
    private User user = null;

    public void logUser (User user) {
        this.user = user;
    }

    public void logOut () {
        this.user = null;
    }
}
