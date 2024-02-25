package com.assignment.LabAppointmentSystem.repository;

import com.assignment.LabAppointmentSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
