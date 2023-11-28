package com.isa.account;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserList userList = new UserList();
    private final String databasePath = "src/main/resources/users.json";

    public  void  addUser (User user) {
        userList.addUser(user);
    }

    public void saveUsersToFile () {
        List<User> existingUserList = loadUsersFromFile();
        existingUserList.addAll(userList.getUsers());
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(databasePath), existingUserList);
            System.out.println("Rejestracja przebiegła pomyślnie :).");
            RoleAssinger.waitingEnter();
        } catch (IOException e) {
            System.out.println("Błąd podczas rejestracji, spróbuj później.");
            RoleAssinger.waitingEnter();
        }
    }

    public  List<User> loadUsersFromFile() {
        try {
            return objectMapper.readValue(new File(databasePath), new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("Błąd podczas rejestracji, spróbuj później.");
            RoleAssinger.waitingEnter();
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
