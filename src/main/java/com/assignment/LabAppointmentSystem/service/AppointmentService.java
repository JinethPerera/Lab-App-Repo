package com.assignment.LabAppointmentSystem.service;

import com.assignment.LabAppointmentSystem.model.Appointment;
import com.assignment.LabAppointmentSystem.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        optionalAppointment.ifPresent(appointment -> {
            appointment.setStatus("Canceled");
            appointmentRepository.save(appointment);
        });
    }

    public void rescheduleAppointment(Long appointmentId, LocalDateTime newDateTime) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        optionalAppointment.ifPresent(appointment -> {
            appointment.setAppointmentDateTime(newDateTime);
            appointmentRepository.save(appointment);
        });
    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        // Implement logic to fetch appointments by patient ID
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByStatus(String status) {
        return appointmentRepository.findByStatusAndAppointmentDateTimeAfter(status, LocalDateTime.now());
    }
}
