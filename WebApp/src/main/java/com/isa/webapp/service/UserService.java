package com.isa.webapp.service;

import com.isa.webapp.model.Subject;
import com.isa.webapp.model.User;
import com.isa.webapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserManager userManager;
    private final UserRepository userRepository;

    public User registerUser(String email, String password, String firstName, String lastName) throws IOException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userManager.registerUser(user);
        return user;
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public void saveUser(){
        User user = new User();
        user.setEmail("test@test.com");
        user.setFirstName("Test");
        userRepository.save(user);
    }



    public Optional<User> loginUser(String email, String password) {
        return userManager.findUserByEmail(email)
                .filter(u -> u.getPassword().equals(password));
    }

    public Map<Subject, List<Integer>> getGradesForLoggedInUser(User user) {
/*        if (user != null && !MapUtils.isEmpty(user.getGrades())) {
            return user.getGrades();
        }*/

        return Collections.emptyMap();
    }

    public Map<Subject, List<Integer>> getFirstNameForLoggedInUser(User user) {
/*        if (user != null && !MapUtils.isEmpty(user.getFirstName())) {
            return user.getFirstName();
        }*/

        return Collections.emptyMap();
    }
}