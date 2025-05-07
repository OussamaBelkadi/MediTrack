package os.patientservice.mapper;

import os.patientservice.dto.PatientRequestDTO;
import os.patientservice.dto.PatientResponseDTO;
import os.patientservice.model.Patient;

import java.time.LocalDate;
import java.util.Optional;

public class PatientMapper {
    public static PatientResponseDTO toPatientResponseDTO(Patient patient) {
        return new PatientResponseDTO(patient.getId().toString(), patient.getName(), patient.getAddress(), patient.getEmail(), patient.getDateOfBirth().toString());
    }

    public static Patient toPatient(PatientRequestDTO patientRequestDTO) {
        Patient.PatientBuilder builder = Patient.builder();
        Optional.ofNullable(patientRequestDTO.name()).map(String::trim).ifPresent(builder::name);
        Optional.ofNullable(patientRequestDTO.address()).map(String::trim).ifPresent(builder::address);
        Optional.ofNullable(patientRequestDTO.email()).map(String::trim).ifPresent(builder::email);
        Optional.ofNullable(patientRequestDTO.dateOfBirth()).map(LocalDate::parse).ifPresent(builder::dateOfBirth);
        Optional.ofNullable(patientRequestDTO.registeredDate()).map(LocalDate::parse).ifPresent(builder::registeredDate);
        return builder.build();
    }
}
