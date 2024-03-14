package com.assignment.LabAppointmentSystem.service;

import com.assignment.LabAppointmentSystem.model.User;
import com.assignment.LabAppointmentSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }



    public void addAdminUser() {

        User existingAdmin = userRepository.findByUsername("Admin");
        if (existingAdmin == null) {
            // Create a new admin user
            User adminUser = new User();
            adminUser.setUsername("Admin");
            adminUser.setPassword("admin123"); // You might want to hash passwords for security

            // Save the admin user to the repository
            userRepository.save(adminUser);
        }


    }
    public boolean isAdmin(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getUsername().equals("Admin") && user.getPassword().equals(password);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
