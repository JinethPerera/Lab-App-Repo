package com.assignment.LabAppointmentSystem.repository;

import com.assignment.LabAppointmentSystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository  extends JpaRepository<Doctor, Long> {
}
