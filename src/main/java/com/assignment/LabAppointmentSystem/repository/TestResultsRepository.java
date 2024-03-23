package com.assignment.LabAppointmentSystem.repository;

import com.assignment.LabAppointmentSystem.model.TestResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  TestResultsRepository extends JpaRepository<TestResults, Long> {
    List<TestResults> findByPatientName(String patientName);

}
