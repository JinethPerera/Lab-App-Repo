package com.assignment.LabAppointmentSystem.controller;

import com.assignment.LabAppointmentSystem.model.Appointment;
import com.assignment.LabAppointmentSystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:3001")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {

        Long patientId = appointment.getPatientId();

        Appointment bookedAppointment = appointmentService.bookAppointment(appointment, patientId);
        return ResponseEntity.ok(bookedAppointment);
    }


    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getMyAppointments(@RequestParam String username) {
        List<Appointment> appointments = appointmentService.getAppointmentsByUsername(username);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }





}
