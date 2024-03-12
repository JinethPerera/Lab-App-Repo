package com.assignment.LabAppointmentSystem.controller;

import com.assignment.LabAppointmentSystem.model.Appointment;
import com.assignment.LabAppointmentSystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab-technician/appointments")
public class LabTechnicianAppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
