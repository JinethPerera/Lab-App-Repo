package com.assignment.LabAppointmentSystem.controller;

import com.assignment.LabAppointmentSystem.model.Type;
import com.assignment.LabAppointmentSystem.model.User;

import com.assignment.LabAppointmentSystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class UserController {

    @Autowired
    private UserService userService;
    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();

        User loggedInUser = userService.loginUser(username, password,email);
        boolean isAdmin = userService.isAdmin(username, password,email);

        if (loggedInUser != null) {
            if (isAdmin) {
                return ResponseEntity.ok("Admin"); // Return "Admin" if user is admin
            } else {
                return ResponseEntity.ok("User"); // Return "User" if user is not admin
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


}
