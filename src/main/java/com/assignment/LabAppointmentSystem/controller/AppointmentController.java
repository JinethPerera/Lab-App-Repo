package com.assignment.LabAppointmentSystem.controller;

import com.assignment.LabAppointmentSystem.model.Appointment;
import com.assignment.LabAppointmentSystem.service.AppointmentService;
import com.assignment.LabAppointmentSystem.service.AppointmentReportService;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:3001")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentReportService appointmentReportService;

    @PostMapping("/book")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {

        Long patientId = appointment.getPatientId();

        Appointment bookedAppointment = appointmentService.bookAppointment(appointment, patientId);
        return ResponseEntity.ok(bookedAppointment);
    }

    @PostMapping("/pay")
    public ResponseEntity<Appointment> payForAppointment(@RequestParam String patientName) {
        Appointment paidAppointment = appointmentService.payForAppointment(patientName);
        return ResponseEntity.ok(paidAppointment);
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getMyAppointments(@RequestParam String username) {
        List<Appointment> appointments = appointmentService.getAppointmentsByUsername(username);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateAppointmentReport(@RequestParam String patientName) {
        List<Appointment> appointments = appointmentService.getAppointmentsByUsername(patientName);
        try {
            byte[] reportBytes = appointmentReportService.generateAppointmentReport(appointments);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "appointments_report.pdf");
            headers.setContentLength(reportBytes.length);

            return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
        } catch (JRException e) {

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
