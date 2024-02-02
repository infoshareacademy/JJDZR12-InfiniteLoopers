package com.isa.webapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class);
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        LOGGER.debug(() -> "Attempting to load user by email: " + email);
        try {
            UserDetails user = userService.findUserByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            LOGGER.info(() -> "User loaded successfully with email: " + email);
            return user;
        } catch (UsernameNotFoundException e) {
            LOGGER.error(() -> "User not found with email: " + email + ", error: " + e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            LOGGER.error(() -> "An unexpected error occurred while loading user by email: " + email + ", error: " + e.getMessage());
            throw new UsernameNotFoundException("An unexpected error occurred", e);
        }
    }
}
