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

    public  void  addUser (User user) {
        userList.addUser(user);
    }

    public void saveUsersToFile (String fileUsers) {
        List<User> existingUserList = loadUsersFromFile(fileUsers);
        existingUserList.addAll(userList.getUsers());
        try {
            objectMapper.writeValue(new File(fileUsers), existingUserList);
            System.out.println("Rejestracja przebiegła pomyślnie.");
        } catch (IOException e) {
            System.out.println("Error podczas rejestracji, sproboj pozniej.");
        }
    }

    public  List<User> loadUsersFromFile (String fileUsers) {
        try {
            return objectMapper.readValue(new File(fileUsers), new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("Error podczas uzyskania danych.");
            return new ArrayList<>();
        }
    }

    public UserList getUserList() {
        return userList;
    }
}
