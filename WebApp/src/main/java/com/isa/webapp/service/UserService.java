package com.isa.webapp.service;

import com.isa.webapp.util.User;
import com.isa.webapp.util.UserManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserManager userManager;

    public UserService(UserManager userManager) {
        this.userManager = userManager;
    }

    public User registerUser (String email, String password, String firstName, String lastName) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userManager.saveUser(user);
        return user;
    }

    public Optional<User> loginUser(String email, String password) {
        return userManager.findUserByEmail(email)
                .filter(u -> u.getPassword().equals(password));
    }
}
