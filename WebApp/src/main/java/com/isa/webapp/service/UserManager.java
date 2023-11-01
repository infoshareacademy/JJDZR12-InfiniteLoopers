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

@Service
public class UserManager {

    public void registerUser(User user) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        File file = new File("/home/user/Desktop/JJDZR12-InfiniteLoopers/WebApp/src/main/resources/users.json"); // Ścieżka do pliku JSON
        List<User> userList = new ArrayList<>();

        if (file.exists()) {
            userList = objectMapper.readValue(file, new TypeReference<List<User>>() {});
        }

        userList.add(user);

        try (FileWriter fileWriter = new FileWriter(file)) {
            writer.writeValue(fileWriter, userList);
        }
    }

}
