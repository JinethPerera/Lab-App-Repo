package com.assignment.LabAppointmentSystem.service;

import com.assignment.LabAppointmentSystem.model.User;

public interface UserServiceInterface {

    User registerUser(User user);

    User loginUser(String username,String password);
}
