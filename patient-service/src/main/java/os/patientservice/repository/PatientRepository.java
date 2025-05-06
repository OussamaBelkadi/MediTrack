package os.patientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import os.patientservice.model.Patient;

import java.util.UUID;

public interface PatientRepository extends   JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);
}
