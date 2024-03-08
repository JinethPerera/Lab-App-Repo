package com.assignment.LabAppointmentSystem.service;

import com.assignment.LabAppointmentSystem.model.Doctor;
import com.assignment.LabAppointmentSystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor updateDoctor(Long id, Doctor newDoctorData) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        existingDoctor.setName(newDoctorData.getName());
        existingDoctor.setSpecialization(newDoctorData.getSpecialization());
        existingDoctor.setAddress(newDoctorData.getAddress());
        existingDoctor.setPhoneNumber(newDoctorData.getPhoneNumber());
        existingDoctor.setEmail(newDoctorData.getEmail());

        return doctorRepository.save(existingDoctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
