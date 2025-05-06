package os.patientservice.mapper;

import os.patientservice.dto.PatientRequestDTO;
import os.patientservice.dto.PatientResponseDTO;
import os.patientservice.model.Patient;

import java.time.LocalDate;
import java.util.Date;

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

    public static Patient toPatient(PatientRequestDTO patientRequestDTO) {
        return Patient.builder()
                .name(patientRequestDTO.name())
                .address(patientRequestDTO.address())
                .email(patientRequestDTO.email())
                .dateOfBirth(LocalDate.parse(patientRequestDTO.dateOfBirth()))
                .registeredDate(LocalDate.parse(patientRequestDTO.registeredDate()))
                .build();
    }
}
