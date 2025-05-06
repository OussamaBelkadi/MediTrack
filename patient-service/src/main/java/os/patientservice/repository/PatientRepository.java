package os.patientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import os.patientservice.model.Patient;

public interface PatientRepository extends   JpaRepository<Patient, Long> { }
