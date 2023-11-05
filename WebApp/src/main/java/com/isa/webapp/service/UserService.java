package com.isa.webapp.service;

<<<<<<< HEAD
import com.isa.webapp.util.User;
import com.isa.webapp.util.UserManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
=======

import com.isa.webapp.model.Subjects;
import com.isa.webapp.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Map;
>>>>>>> origin/JJDZR12IL21-22

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

    objectMapper.writerWithDefaultPrettyPrinter().writeValue(userFile, tasks)
}
