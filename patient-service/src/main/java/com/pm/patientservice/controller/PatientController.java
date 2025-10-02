package com.pm.patientservice.controller;

import com.pm.patientservice.DTO.PatientDTO;
import com.pm.patientservice.DTO.PatientRequestDTO;
import com.pm.patientservice.DTO.validation.CreatePatientValidationGroup;
import com.pm.patientservice.entity.Patient;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("patient")
@Tag(name="Patient", description = "API for managing Patients")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("getAllPatients")
    @Operation(summary = "Get all Patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients(){
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping("save")
    @Operation(summary = "Save a new Patient")
    public ResponseEntity<PatientDTO> savePatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientDTO patient = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patient);
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Update a Patient")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientDTO patient = patientService.updatePatient(id,patientRequestDTO);
        return ResponseEntity.ok().body(patient);
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "Delete existing Patient")
    public ResponseEntity<String> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.ok().body("Patient Deleted");
    }
}
