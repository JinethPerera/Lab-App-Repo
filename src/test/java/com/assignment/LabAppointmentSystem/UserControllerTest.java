package com.assignment.LabAppointmentSystem;

import com.assignment.LabAppointmentSystem.controller.UserController;
import com.assignment.LabAppointmentSystem.model.User;
import com.assignment.LabAppointmentSystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User testUser;

    @BeforeEach
    void setUp() {

        testUser = new User();
        testUser.setUsername("testUser");
        testUser.setPassword("testPassword");
        testUser.setEmail("test@example.com");
    }


    @Test
    void testRegisterUser() {

        when(userService.registerUser(any(User.class))).thenReturn(testUser);

        ResponseEntity<?> responseEntity = userController.registerUser(testUser);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User registered successfully", responseEntity.getBody());
    }

    @Test
    void testLoginUserValidCredentials() {

        when(userService.loginUser(anyString(), anyString(), anyString())).thenReturn(testUser);

        ResponseEntity<?> responseEntity = userController.loginUser(testUser);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User", responseEntity.getBody());

        verify(userService, times(1)).loginUser(eq(testUser.getUsername()), eq(testUser.getPassword()), eq(testUser.getEmail()));
    }

    @Test
    void testLoginUserInvalidCredentials() {

        when(userService.loginUser(anyString(), anyString(), anyString())).thenReturn(null);

        ResponseEntity<?> responseEntity = userController.loginUser(testUser);


        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid username or password", responseEntity.getBody());

        verify(userService, times(1)).loginUser(eq(testUser.getUsername()), eq(testUser.getPassword()), eq(testUser.getEmail()));
    }
}
