package com.isa.webapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.isa.webapp.model.User;
import com.isa.webapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserManager {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final PasswordEncoder passwordEncoder;

    private static final String USERS_FILE = "users.json";

    public void registerUser(User user) throws IOException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("Uzytkownik z takim email instnieje");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
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
        return userRepository.findByEmail(email);
    }
}