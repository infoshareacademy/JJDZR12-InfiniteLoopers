package com.isa.webapp.security;

import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import com.isa.webapp.service.UserManager;
import com.isa.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SetupDataLoader(UserService userService, UserManager userManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        boolean adminExists = userService.getUsersByRole(UserRole.ADMIN).stream()
                .anyMatch(user -> user.getUserRole() == UserRole.ADMIN);
        if (adminExists) {
            return;
        }
        User adminUser = new User();
        adminUser.setFirstName("Admin");
        adminUser.setLastName("User");
        adminUser.setPassword(encodePassword("admin"));
        adminUser.setEmail("admin@example.com");
        adminUser.setUserRole(UserRole.ADMIN);
        adminUser.setApproved(true);

        try {
            userService.registerUser(adminUser);
        } catch (Exception e) {
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
