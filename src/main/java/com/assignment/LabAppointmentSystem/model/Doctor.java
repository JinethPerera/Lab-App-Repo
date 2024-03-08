package com.assignment.LabAppointmentSystem.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.Specification;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    private String address;
    private String phoneNumber;
    private String email;

    public Doctor() {}

    public Doctor(String name, Specialization specialization, String address, String phoneNumber, String email) {
        this.name = name;
        this.specialization = specialization;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        // Initialize other fields as needed
    }

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
