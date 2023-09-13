package com.isa.account;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserList userList = new UserList();
    private final String databasePath = "users.json";

    public  void  addUser (User user) {
        userList.addUser(user);
    }

    public void saveUsersToFile () {
        List<User> existingUserList = loadUsersFromFile();
        existingUserList.addAll(userList.getUsers());
        try {
            objectMapper.writeValue(new File(databasePath), existingUserList);
            System.out.println("Rejestracja przebiegła pomyślnie.");
        } catch (IOException e) {
            System.out.println("Error podczas rejestracji, sprobuj pozniej.");
        }
    }

    public  List<User> loadUsersFromFile() {
        try {
            return objectMapper.readValue(new File(databasePath), new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("Error podczas uzyskania danych.");
            return new ArrayList<>();
        }
    }

    public  void updateUser (List<User> userList) throws IOException {
        objectMapper.writeValue(new File(databasePath), userList);
    }

    public UserList getUserList() {
        return userList;
    }
}
