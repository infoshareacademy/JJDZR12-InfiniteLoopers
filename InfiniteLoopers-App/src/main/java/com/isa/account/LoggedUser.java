package com.isa.account;

public class LoggedUser {
    public User getUser() {
        return user;
    }

    private User user = null;

    public void logUser(User user) {
        this.user = user;
    }
}


