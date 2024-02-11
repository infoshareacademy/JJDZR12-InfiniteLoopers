package com.isa.webapp;

import com.isa.webapp.model.Grade;
import com.isa.webapp.model.Subject;
import com.isa.webapp.model.User;
import com.isa.webapp.repository.GradeRepository;
import com.isa.webapp.repository.UserRepository;
import com.isa.webapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserByEmailShouldReturnUserWhenExists() {
        // given
        String email = "user@example.com";
        User user = new User();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // when
        User foundUser = userService.getUserByEmail(email);

        // then
        assertNotNull(foundUser);
    }

    @Test
    void getUserByEmailShouldReturnNullWhenUserDoesNotExist() {
        // given
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // when
        User foundUser = userService.getUserByEmail(email);

        // then
        assertNull(foundUser);
    }

    @Test
    void getUserByEmailShouldHandleExceptionFromRepository() {
        // given
        String email = "user@example.com";
        when(userRepository.findByEmail(email)).thenThrow(new RuntimeException("Database error"));

        // when & then
        assertThrows(RuntimeException.class, () -> userService.getUserByEmail(email));
    }

    @Test
    void registerUserShouldThrowWhenEmailExists() {
        // given
        String email = "existing@example.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // when & then
        assertThrows(IllegalStateException.class, () -> userService.registerUser(user));
    }
    @Test
    void findUserByEmailShouldReturnEmptyWhenUserDoesNotExist() {
        // given
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // when
        Optional<User> result = userService.findUserByEmail(email);

        // then
        assertTrue(result.isEmpty());
    }
    @Test
    void registerUserShouldThrowExceptionWhenEmailAlreadyExists() {
        // given
        User newUser = new User();
        newUser.setEmail("existing@example.com");
        newUser.setPassword("password123");
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        // when & then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> userService.registerUser(newUser));

        assertEquals("User with that email already exist.", thrown.getMessage());
    }
    @Test
    void getGradesForLoggedInUserShouldReturnEmptyMapWhenNoGrades() {
        // given
        User user = new User();
        when(gradeRepository.findByUserUuid(user.getUuid())).thenReturn(Collections.emptyList());

        // when
        Map<Subject, List<Double>> gradesMap = userService.getGradesForLoggedInUser(user);

        // then
        assertTrue(gradesMap.isEmpty(), "Map should be empty when no grades are found.");
    }
    @Test
    void convertGradesToMapShouldReturnEmptyMapWhenNoGradesProvided() {
        // given
        List<Grade> emptyGradesList = Collections.emptyList();

        // when
        Map<Subject, List<Double>> result = UserService.convertGradesToMap(emptyGradesList);

        // then
        assertTrue(result.isEmpty(), "Map should be empty when no grades are provided.");
    }
}
