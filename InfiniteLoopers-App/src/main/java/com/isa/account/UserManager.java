package com.isa.account;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<User> users = new ArrayList<>();
    public  void  addUser (User user) {
        users.add(user);
    }

    public void saveUsersToFile (String fileUsers) {
        try {
            objectMapper.writeValue(new File(fileUsers), users);
            System.out.println("Rejestracja przebiegła pomyślnie.");
        } catch (IOException e) {
            System.out.println("Error podczas rejestracji, sproboj pozniej.");
        }
    }

    public  List<User> loadUsersFromFile (String fileUsers) {
        try {
            return objectMapper.readValue(new File(fileUsers), objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        } catch (IOException e) {
            System.out.println("Error podczas uzyskania dannych.");
            return new ArrayList<>();
        }
    }
}
