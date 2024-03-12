package com.assignment.LabAppointmentSystem.service;

import com.assignment.LabAppointmentSystem.model.TestResults;
import com.assignment.LabAppointmentSystem.repository.TestResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultService {

    private final TestResultsRepository testResultsRepository;

    @Autowired
    public TestResultService(TestResultsRepository testResultsRepository) {
        this.testResultsRepository = testResultsRepository;
    }

    public void saveTestResult(TestResults testResult) {

        testResultsRepository.save(testResult);
    }

    public List<TestResults> getTestResultsByPatientName(String patientName) {
        return testResultsRepository.findByPatientName(patientName);
    }

}
