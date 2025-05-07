package os.patientservice.service;

import org.springframework.stereotype.Service;
import os.patientservice.dto.PatientRequestDTO;
import os.patientservice.dto.PatientResponseDTO;
import os.patientservice.exception.EmailAlreadyExistsException;
import os.patientservice.exception.PatientNotFoundException;
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
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient with id " + id + " not found."));
        Patient patientData = PatientMapper.toPatient(patientRequestDTO);
        if (patientRepository.existsByEmailAndIdNot(patientData.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Email " + patientRequestDTO.email() + " already exists.");
        }
        patient.setName(patientData.getName());
        patient.setAddress(patientData.getAddress());
        patient.setEmail(patientData.getEmail());
        patient.setDateOfBirth(patientData.getDateOfBirth());

        patient = patientRepository.save(patient);
        return PatientMapper.toPatientResponseDTO(patient);

    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
