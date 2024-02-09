package com.isa.webapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {

        try {
            UserDetails user = userService.findUserByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            log.info("User loaded successfully with email: {}", email);
            return user;
        } catch (UsernameNotFoundException e) {
            log.error("User not found with email: {}, error: {}", email, e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            log.error("An unexpected error occurred while loading user by email: {}, error: {}", email, e.getMessage());
            throw new UsernameNotFoundException("An unexpected error occurred", e);
        }
    }
}
