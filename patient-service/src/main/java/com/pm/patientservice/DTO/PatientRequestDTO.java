package com.pm.patientservice.DTO;

import com.pm.patientservice.DTO.validation.CreatePatientValidationGroup;
import jakarta.validation.constraints.*;

public class PatientRequestDTO {
    @NotBlank(message = "Patient Name is required.")
    @NotEmpty(message = "Patient name should not be empty.")
    @Size(max = 100, message = "Yeh apna naam bata raha hai ki address.")
    private String name;

    @NotBlank(message = "Patient Address is required.")
    @Size(max = 200, message = "Address can not exceed 200 characters.")
    private String address;

    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Patient Email-id is required for contact purpose.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotBlank(message = "Date of Birth of patient is required.")
    private String dateOfBirth;

    @NotBlank(message = "Registration Date should not be empty")
    private String registrationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
