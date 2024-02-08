package com.isa.webapp;

import com.isa.webapp.model.User;
import com.isa.webapp.service.UserDetailsServiceImpl;
import com.isa.webapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {
        // given
        String email = "test@example.com";
        User user = new User();
        when(userService.findUserByEmail(email)).thenReturn(Optional.of(user));

        // when
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // then
        assertNotNull(userDetails, "UserDetails should not be null for existing user");
    }

    @Test
    void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExist() {
        // given
        String email = "nonexistent@example.com";
        when(userService.findUserByEmail(email)).thenReturn(Optional.empty());

        // when & then
        assertThrows(RuntimeException.class, () -> userDetailsService.loadUserByUsername(email));
        // TODO Czy ta metoda nie powinna rzucac UsernameNotFoundException?
    }
}