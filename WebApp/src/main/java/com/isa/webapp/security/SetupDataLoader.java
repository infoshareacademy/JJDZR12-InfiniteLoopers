package com.isa.webapp.security;

import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import com.isa.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;


    @Autowired
    public SetupDataLoader(UserService userService) {
        this.userService = userService;
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
        adminUser.setPassword(("admin"));
        adminUser.setEmail("admin@example.com");
        adminUser.setUserRole(UserRole.ADMIN);
        adminUser.setApproved(true);

        userService.registerUser(adminUser);

    }
}
