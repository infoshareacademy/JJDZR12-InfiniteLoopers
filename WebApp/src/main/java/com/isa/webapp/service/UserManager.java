package com.isa.webapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserManager {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final PasswordEncoder passwordEncoder;

    private static final String USERS_FILE = "users.json";

    public UserManager(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private void saveUserList(List<User> userList) throws IOException {
        File file = new File(USERS_FILE);
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        try (FileWriter fileWriter = new FileWriter(file)) {
            writer.writeValue(fileWriter, userList);
        }
    }

    public List<User> getAllUsers() {
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

    public void approveUserRoles(String userId, List<UserRole> roles) throws IOException {
        List<User> userList = getAllUsers();
        userList.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .ifPresent(user -> {
                    user.setApprovedRoles(roles);
                    user.setApproved(true);
                });
        saveUserList(userList);
    }
    public void deleteUser(String userId) throws IOException {
        List<User> userList = getAllUsers()
                .stream()
                .filter(user -> !user.getId().equals(userId))
                .collect(Collectors.toList());
        saveUserList(userList);
    }

    public void updateUserRole(String userId, UserRole newRole) throws IOException {
        List<User> userList = getAllUsers();
        userList.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .ifPresent(user -> user.setUserRole(newRole));
        saveUserList(userList);
    }

    public User getUserById(String userId) {
        return getAllUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
    public void updateUser(User updatedUser) throws IOException {
        List<User> userList = getAllUsers();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(updatedUser.getId())) {
                userList.set(i, updatedUser);
                saveUserList(userList);
                return;
            }
        }
        throw new RuntimeException("User not found with id: " + updatedUser.getId());
    }

    public List<User> getUnapprovedUsers() {
        return getAllUsers().stream()
                .filter(user -> !user.isApproved())
                .collect(Collectors.toList());
    }

}