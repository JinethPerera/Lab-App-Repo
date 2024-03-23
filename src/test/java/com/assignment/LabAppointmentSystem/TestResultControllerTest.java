package com.assignment.LabAppointmentSystem;

import com.assignment.LabAppointmentSystem.controller.TestResultController;
import com.assignment.LabAppointmentSystem.model.TestResults;
import com.assignment.LabAppointmentSystem.service.TestResultService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestResultControllerTest {

    @Mock
    private TestResultService testResultService;

    @InjectMocks
    private TestResultController testResultController;

    private TestResults testResult;

    @BeforeEach
    void setUp() {
        testResult = new TestResults();

    }

    @Test
    void testSubmitTestResult() {
        ResponseEntity<String> responseEntity = testResultController.submitTestResult(testResult);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Test result submitted successfully", responseEntity.getBody());
    }

    @Test
    void testGetTestResultsByPatientName() {
        List<TestResults> testResults = new ArrayList<>();
        testResults.add(testResult);
        when(testResultService.getTestResultsByPatientName("patientName")).thenReturn(testResults);

        ResponseEntity<List<TestResults>> responseEntity = testResultController.getTestResultsByPatientName("patientName");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testResults, responseEntity.getBody());
    }
}