package com.assignment.LabAppointmentSystem.controller;

import com.assignment.LabAppointmentSystem.model.LabTechnician;
import com.assignment.LabAppointmentSystem.service.LabTechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class LabTechnicianController {
    private final LabTechnicianService labTechnicianService;

    @Autowired
    public LabTechnicianController(LabTechnicianService labTechnicianService) {
        this.labTechnicianService = labTechnicianService;
    }

    @PostMapping("/lab-technicians")
    public ResponseEntity<LabTechnician> addLabTechnician(@RequestBody LabTechnician labTechnician) {
        LabTechnician newLabTechnician = labTechnicianService.addLabTechnician(labTechnician);
        return new ResponseEntity<>(newLabTechnician, HttpStatus.CREATED);
    }

    @GetMapping("/lab-technicians")
    public ResponseEntity<List<LabTechnician>> getAllLabTechnicians() {
        List<LabTechnician> labTechnicians = labTechnicianService.getAllLabTechnicians();
        return new ResponseEntity<>(labTechnicians, HttpStatus.OK);
    }

    @PutMapping("/lab-technicians/{id}")
    public ResponseEntity<LabTechnician> updateLabTechnician(@PathVariable Long id, @RequestBody LabTechnician labTechnician) {
        LabTechnician updatedLabTechnician = labTechnicianService.updateLabTechnician(id, labTechnician);
        return new ResponseEntity<>(updatedLabTechnician, HttpStatus.OK);
    }

    @DeleteMapping("/lab-technicians/{id}")
    public ResponseEntity<Void> deleteLabTechnician(@PathVariable Long id) {
        labTechnicianService.deleteLabTechnician(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
