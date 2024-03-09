package com.assignment.LabAppointmentSystem.service;

import com.assignment.LabAppointmentSystem.model.Appointment;
import com.assignment.LabAppointmentSystem.model.Doctor;
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
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }



}
