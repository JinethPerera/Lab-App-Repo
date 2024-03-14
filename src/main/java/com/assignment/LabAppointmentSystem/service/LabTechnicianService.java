package com.assignment.LabAppointmentSystem.service;

import com.assignment.LabAppointmentSystem.model.LabTechnician;
import com.assignment.LabAppointmentSystem.repository.LabTechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabTechnicianService {



    private final LabTechnicianRepository labTechnicianRepository;

    @Autowired
    public LabTechnicianService(LabTechnicianRepository labTechnicianRepository) {
        this.labTechnicianRepository = labTechnicianRepository;
    }

    public LabTechnician addLabTechnician(LabTechnician labTechnician) {
        return labTechnicianRepository.save(labTechnician);
    }

    public List<LabTechnician> getAllLabTechnicians() {
        return labTechnicianRepository.findAll();
    }

    public LabTechnician updateLabTechnician(Long id, LabTechnician newLabTechnicianData) {
        LabTechnician existingLabTechnician = labTechnicianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lab Technician not found with id: " + id));

        existingLabTechnician.setName(newLabTechnicianData.getName());
        existingLabTechnician.setSpecialization(newLabTechnicianData.getSpecialization());
        existingLabTechnician.setAddress(newLabTechnicianData.getAddress());
        existingLabTechnician.setPhoneNumber(newLabTechnicianData.getPhoneNumber());
        existingLabTechnician.setEmail(newLabTechnicianData.getEmail());
        existingLabTechnician.setPassword(newLabTechnicianData.getPassword());
        existingLabTechnician.setType(newLabTechnicianData.getType());

        return labTechnicianRepository.save(existingLabTechnician);
    }

    public void deleteLabTechnician(Long id) {
        labTechnicianRepository.deleteById(id);
    }

    public LabTechnician login(String email, String password) {
        return labTechnicianRepository.findByEmailAndPassword(email, password);
    }

}
