package com.assignment.LabAppointmentSystem;



import com.assignment.LabAppointmentSystem.controller.AppointmentController;
import com.assignment.LabAppointmentSystem.model.Appointment;
import com.assignment.LabAppointmentSystem.service.AppointmentReportService;
import com.assignment.LabAppointmentSystem.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private AppointmentReportService appointmentReportService;

    @InjectMocks
    private AppointmentController appointmentController;

    private Appointment testAppointment;

    @BeforeEach
    void setUp() {
        testAppointment = new Appointment();
        testAppointment.setPatientId(1L);
        // Set other properties as required
    }

    @Test
    void testBookAppointment() {
        when(appointmentService.bookAppointment(any(Appointment.class), any(Long.class))).thenReturn(testAppointment);

        ResponseEntity<Appointment> responseEntity = appointmentController.bookAppointment(testAppointment);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testAppointment, responseEntity.getBody());
    }

    @Test
    void testPayForAppointment() {
        when(appointmentService.payForAppointment(any(String.class))).thenReturn(testAppointment);

        ResponseEntity<Appointment> responseEntity = appointmentController.payForAppointment("patientName");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testAppointment, responseEntity.getBody());
    }

    @Test
    void testGetMyAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(testAppointment);
        when(appointmentService.getAppointmentsByUsername(any(String.class))).thenReturn(appointments);

        ResponseEntity<List<Appointment>> responseEntity = appointmentController.getMyAppointments("username");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(appointments, responseEntity.getBody());
    }

    @Test
    void testGenerateAppointmentReport() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(testAppointment);
        when(appointmentService.getAppointmentsByPatientName(any(String.class))).thenReturn(appointments);

        try {
            byte[] mockReportBytes = "Mock Report Data".getBytes();
            when(appointmentReportService.generateAppointmentReport(appointments)).thenReturn(mockReportBytes);

            ResponseEntity<byte[]> responseEntity = appointmentController.generateAppointmentReport("patientName");

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(mockReportBytes.length, responseEntity.getBody().length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
