package os.patientservice.service;

import org.springframework.stereotype.Service;
import os.patientservice.dto.PatientRequestDTO;
import os.patientservice.dto.PatientResponseDTO;
import os.patientservice.exception.EmailAlreadyExistsException;
import os.patientservice.mapper.PatientMapper;
import os.patientservice.model.Patient;
import os.patientservice.repository.PatientRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {
    public PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toPatientResponseDTO).collect(Collectors.toList());
    }

    public PatientResponseDTO addPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.email())) {
            throw new EmailAlreadyExistsException("Email " + patientRequestDTO.email() + " already exists.");
        }
        Patient patient = PatientMapper.toPatient(patientRequestDTO);
        Patient savedPatient = patientRepository.save(patient);
        return PatientMapper.toPatientResponseDTO(savedPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        if (patientRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Patient with id " + id + " not found.");
        } else {
            Patient patient = PatientMapper.toPatient(patientRequestDTO);
            if (patientRepository.existsByEmail(patient.getEmail())) {
                throw new EmailAlreadyExistsException("Email " + patientRequestDTO.email() + " already exists.");
            }
            patientRepository.save(patient);
            return PatientMapper.toPatientResponseDTO(patient);
        }

    }
}
