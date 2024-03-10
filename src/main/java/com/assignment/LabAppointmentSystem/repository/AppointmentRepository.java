package com.assignment.LabAppointmentSystem.repository;

import com.assignment.LabAppointmentSystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientName(String patientName);
    List<Appointment> findByStatusAndAppointmentDateTimeAfter(String status, LocalDateTime dateTime);


}
