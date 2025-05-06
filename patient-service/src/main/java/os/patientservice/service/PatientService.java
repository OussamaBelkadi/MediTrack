package os.patientservice.service;

import org.springframework.stereotype.Service;
import os.patientservice.dto.PatientResponseDTO;
import os.patientservice.model.Patient;
import os.patientservice.repository.PatientRepository;
import os.patientservice.mapper.PatientMapper;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    public PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toPatientResponseDTO)
                .collect(Collectors.toList());
    }
}
