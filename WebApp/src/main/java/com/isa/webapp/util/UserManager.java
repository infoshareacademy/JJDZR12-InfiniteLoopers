package com.isa.webapp.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_FILE = "src/main/resources/users.json";

    private List<User> getAllUsers() {
        try {
            return objectMapper.readValue(new File(USERS_FILE), new TypeReference<List<User>>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public void saveUser(User user) {
        List<User> users = getAllUsers();
        users.add(user);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(USERS_FILE), users);
        } catch (IOException e) {
            //TODO
        }
    }

    public Optional<User> findUserByEmail(String email) {
        return getAllUsers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }
}
