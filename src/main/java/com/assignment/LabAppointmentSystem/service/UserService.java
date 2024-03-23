package com.assignment.LabAppointmentSystem.service;

import com.assignment.LabAppointmentSystem.model.User;
import com.assignment.LabAppointmentSystem.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Override
    public User registerUser(User user) {
        User registeredUser = userRepository.save(user);
        sendRegistrationEmail(registeredUser.getEmail());
        return registeredUser;
    }

    private void sendRegistrationEmail(String userEmail) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(userEmail);
            helper.setSubject("Registration Confirmation");
            helper.setText("Dear user, \n\nYou have successfully registered with our Lab Appointment System.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        emailSender.send(message);
    }
    @Override
    public User loginUser(String username, String password ,String email) {
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
            adminUser.setPassword("admin123");


            userRepository.save(adminUser);
        }


    }
    public boolean isAdmin(String username, String password , String email) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getUsername().equals("Admin") && user.getPassword().equals(password);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
