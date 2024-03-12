package com.assignment.LabAppointmentSystem.controller;

import com.assignment.LabAppointmentSystem.model.TestResults; // Update import statement
import com.assignment.LabAppointmentSystem.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testResults")
@CrossOrigin(origins = "http://localhost:3001")
public class TestResultController {

    private final TestResultService testResultService;

    @Autowired
    public TestResultController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }


    @PostMapping("/submit")
    public ResponseEntity<String> submitTestResult(@RequestBody TestResults testResult) {

        testResultService.saveTestResult(testResult);


        return ResponseEntity.status(HttpStatus.OK).body("Test result submitted successfully");
    }
    @GetMapping
    public ResponseEntity<List<TestResults>> getTestResultsByPatientName(@RequestParam String patientName) {
        List<TestResults> testResults = testResultService.getTestResultsByPatientName(patientName);
        return ResponseEntity.ok(testResults);
    }
}
