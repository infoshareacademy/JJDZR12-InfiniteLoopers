package com.isa.webapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.webapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class LoginController {

    // Ścieżka do pliku users.json (zmień na właściwą)
    private static final String USERS_JSON_FILE = "/home/user/Desktop/JJDZR12-InfiniteLoopers/WebApp/src/main/resources/users.json";


    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        if (loginUser(user)) {
            redirectAttributes.addFlashAttribute("successMessage", "Zalogowano pomyślnie!");
            // Tutaj dodaj logikę przekierowania do odpowiedniej strony po zalogowaniu
            return "redirect:/"; // Przykładowa przekierowująca ścieżka
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Błąd logowania. Spróbuj ponownie.");
            return "redirect:/login";
        }
    }

    private boolean loginUser(User user) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = mapper.readValue(Files.newBufferedReader(Paths.get(USERS_JSON_FILE)), new TypeReference<List<User>>(){});

            for (User storedUser : users) {
                if (user.getLoginUser().equals(storedUser.getLoginUser()) && user.getPasswordUser().equals(storedUser.getPasswordUser())) {
                    return true; // Logowanie pomyślne
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Logowanie nieudane
    }
}
