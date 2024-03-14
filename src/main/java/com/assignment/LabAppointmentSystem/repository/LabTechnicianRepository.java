package com.assignment.LabAppointmentSystem.repository;

import com.assignment.LabAppointmentSystem.model.LabTechnician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabTechnicianRepository extends JpaRepository<LabTechnician, Long> {
    LabTechnician findByEmailAndPassword(String email, String password);
}
