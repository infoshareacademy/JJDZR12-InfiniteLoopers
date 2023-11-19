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
        Optional<User> existingUser = findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("Uzytkownik z takim email instnieje");
        }
        List<User> userList = getAllUsers();
        userList.add(user);
        saveUserList(userList);
    }

    private void saveUserList(List<User> userList) throws IOException {
        File file = new File(USERS_FILE);
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        try (FileWriter fileWriter = new FileWriter(file)) {
            writer.writeValue(fileWriter, userList);
        }
    }

    private List<User> getAllUsers() {
        try {
            File file = new File(USERS_FILE);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<User>>() {});
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public Optional<User> findUserByEmail(String email) {
        return getAllUsers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }
}