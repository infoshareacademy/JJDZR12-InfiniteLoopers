package com.isa.webapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.isa.webapp.model.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserManager {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_FILE = "users.json";

    public void registerUser(User user) throws IOException {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        File file = new File(USERS_FILE);
        List<User> userList = new ArrayList<>();

        if (file.exists()) {
            userList = objectMapper.readValue(file, new TypeReference<List<User>>() {
            });
        }

        userList.add(user);

        try (FileWriter fileWriter = new FileWriter(file)) {
            writer.writeValue(fileWriter, userList);
        }
    }

    private List<User> getAllUsers() {
        try {
            return objectMapper.readValue(new File(USERS_FILE), new TypeReference<List<User>>() {
            });
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