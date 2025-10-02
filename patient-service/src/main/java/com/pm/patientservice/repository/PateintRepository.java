package com.pm.patientservice.repository;

import com.pm.patientservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PateintRepository extends JpaRepository<Patient, UUID> {
    public boolean existsByEmail(String email);
}
