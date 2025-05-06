package os.patientservice.mapper;

import os.patientservice.dto.PatientResponseDTO;
import os.patientservice.model.Patient;

public class PatientMapper {
    public static PatientResponseDTO toPatientResponseDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getId().toString(),
                patient.getName(),
                patient.getAddress(),
                patient.getEmail(),
                patient.getDateOfBirth().toString()
        );
    }
}
