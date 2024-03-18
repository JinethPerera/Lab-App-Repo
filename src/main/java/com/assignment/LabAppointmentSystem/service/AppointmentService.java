package com.assignment.LabAppointmentSystem.service;

import com.assignment.LabAppointmentSystem.model.Appointment;
import com.assignment.LabAppointmentSystem.model.TestResults;
import com.assignment.LabAppointmentSystem.repository.AppointmentRepository;
import com.assignment.LabAppointmentSystem.repository.TestResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;



    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment bookAppointment(Appointment appointment, Long patientId) {
        appointment.setPatientId(patientId); // Set the patient ID
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByUsername(String username) {
        return appointmentRepository.findByPatientName(username);
    }

    public Appointment payForAppointment(String patientName) {
        List<Appointment> appointments = appointmentRepository.findByPatientName(patientName);
        if(appointments.isEmpty()) {
            throw new RuntimeException("Appointment not found for patient: " + patientName);
        }

        // Assuming here that there's only one appointment per patient, if not, adjust accordingly
        Appointment appointment = appointments.get(0);
        appointment.setStatus("paid");
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByPatientName(String patientName) {
        return appointmentRepository.findByPatientName(patientName);
    }






}
