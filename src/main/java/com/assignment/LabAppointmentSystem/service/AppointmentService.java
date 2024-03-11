package com.assignment.LabAppointmentSystem.service;

import com.assignment.LabAppointmentSystem.model.Appointment;
import com.assignment.LabAppointmentSystem.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(Appointment appointment, Long patientId) {
        appointment.setPatientId(patientId); // Set the patient ID
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }



}
