package com.isa.webapp.controller;

import com.isa.webapp.service.UserService;
import com.isa.webapp.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String email,
                                      @RequestParam String confirmEmail,
                                      @RequestParam String password,
                                      @RequestParam String firstName,
                                      @RequestParam String lastName) {

        if (!email.equals(confirmEmail)) {
            return ResponseEntity.badRequest().body("Weryfikacja email nie udała się");
        }
        User user = userService.registerUser(email,password,firstName,lastName);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        Optional<User> userOpt = userService.loginUser(email, password);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body((Object) "Dane niepoprawne");
        }
    }
}
