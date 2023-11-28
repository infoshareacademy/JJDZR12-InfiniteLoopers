package com.isa.webapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserManager userManager;

    public UserDetailsServiceImpl(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userManager.findUserByEmail(email).orElseThrow(RuntimeException::new);
    }
}
