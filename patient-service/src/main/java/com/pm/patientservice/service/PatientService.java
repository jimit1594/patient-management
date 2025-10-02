package com.pm.patientservice.service;

import com.pm.patientservice.DTO.PatientDTO;
import com.pm.patientservice.DTO.PatientRequestDTO;
import com.pm.patientservice.entity.Patient;
import com.pm.patientservice.exception.EmailAlreadyExistException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.repository.PateintRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private PateintRepository patientRepository;

    public PatientService(PateintRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientDTO> getAllPatients(){
        List<Patient> patients=patientRepository.findAll();
        List<PatientDTO> patientDTOs= patients.stream().map(PatientMapper::toDTO).toList();

        return patientDTOs;
    }

    public PatientDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            // if email already exists, do not create patient
            throw new EmailAlreadyExistException("Email " + patientRequestDTO.getEmail() + " already exist.");
        }
        Patient patient= patientRepository.save(PatientMapper.fromDTO(patientRequestDTO));
        return PatientMapper.toDTO(patient);
    }

    public PatientDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
       Patient patient= patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient Not Found."));
       // Do not update email Id.
       patient.setName(patientRequestDTO.getName());
       patient.setAddress(patientRequestDTO.getAddress());
       patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegistrationDate()));
       patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
       patientRepository.save(patient);
       return PatientMapper.toDTO(patient);

    }

    public void deletePatient(UUID id) {
        if(!patientRepository.existsById(id)){
            // if email already exists, do not create patient
            throw new PatientNotFoundException("Patient Not Found.");
        }
        patientRepository.deleteById(id);
    }
}
