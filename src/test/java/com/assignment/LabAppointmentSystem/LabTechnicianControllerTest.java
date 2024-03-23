package com.assignment.LabAppointmentSystem;

import com.assignment.LabAppointmentSystem.controller.LabTechnicianController;
import com.assignment.LabAppointmentSystem.model.LabTechnician;
import com.assignment.LabAppointmentSystem.model.LoginRequest;
import com.assignment.LabAppointmentSystem.service.LabTechnicianService;
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
public class LabTechnicianControllerTest {

    @Mock
    private LabTechnicianService labTechnicianService;

    @InjectMocks
    private LabTechnicianController labTechnicianController;

    private LabTechnician testLabTechnician;

    @BeforeEach
    void setUp() {
        testLabTechnician = new LabTechnician();

    }

    @Test
    void testAddLabTechnician() {
        when(labTechnicianService.addLabTechnician(any(LabTechnician.class))).thenReturn(testLabTechnician);

        ResponseEntity<LabTechnician> responseEntity = labTechnicianController.addLabTechnician(testLabTechnician);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testLabTechnician, responseEntity.getBody());
    }

    @Test
    void testGetAllLabTechnicians() {
        List<LabTechnician> labTechnicians = new ArrayList<>();
        labTechnicians.add(testLabTechnician);
        when(labTechnicianService.getAllLabTechnicians()).thenReturn(labTechnicians);

        ResponseEntity<List<LabTechnician>> responseEntity = labTechnicianController.getAllLabTechnicians();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(labTechnicians, responseEntity.getBody());
    }

    @Test
    void testUpdateLabTechnician() {
        when(labTechnicianService.updateLabTechnician(eq(1L), any(LabTechnician.class))).thenReturn(testLabTechnician);

        ResponseEntity<LabTechnician> responseEntity = labTechnicianController.updateLabTechnician(1L, testLabTechnician);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testLabTechnician, responseEntity.getBody());
    }

    @Test
    void testDeleteLabTechnician() {
        ResponseEntity<Void> responseEntity = labTechnicianController.deleteLabTechnician(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testLogin_Success() {
        when(labTechnicianService.login(eq("test@example.com"), eq("password"))).thenReturn(testLabTechnician);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        ResponseEntity<LabTechnician> responseEntity = labTechnicianController.login(loginRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testLabTechnician, responseEntity.getBody());
    }

    @Test
    void testLogin_Failure() {
        when(labTechnicianService.login(eq("test@example.com"), eq("wrongPassword"))).thenReturn(null);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("wrongPassword");

        ResponseEntity<LabTechnician> responseEntity = labTechnicianController.login(loginRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }
}
