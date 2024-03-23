package com.assignment.LabAppointmentSystem;



import com.assignment.LabAppointmentSystem.controller.LabTechnicianAppointmentController;
import com.assignment.LabAppointmentSystem.model.Appointment;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LabTechnicianAppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private LabTechnicianAppointmentController labTechnicianAppointmentController;

    private Appointment testAppointment;

    @BeforeEach
    void setUp() {
        testAppointment = new Appointment();

    }

    @Test
    void testGetAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(testAppointment);
        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        ResponseEntity<List<Appointment>> responseEntity = labTechnicianAppointmentController.getAllAppointments();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(appointments, responseEntity.getBody());
    }
}
