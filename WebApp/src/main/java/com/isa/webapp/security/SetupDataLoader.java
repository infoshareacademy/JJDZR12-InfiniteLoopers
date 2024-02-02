package com.isa.webapp.security;

import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import com.isa.webapp.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final UserManager userManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SetupDataLoader(UserManager userManager, PasswordEncoder passwordEncoder) {
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;


        User adminUser = userManager.findUserByEmail("admin@example.com").orElse(null);
        if (adminUser == null) {
            adminUser = new User();
            adminUser.setFirstName("Admin");
            adminUser.setLastName("User");
            adminUser.setPassword("admin");
            adminUser.setEmail("admin@example.com");
            adminUser.setUserRole(UserRole.ADMIN);
            adminUser.setApproved(true);
            try {
                userManager.registerUser(adminUser);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        alreadySetup = true;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}

