package os.patientservice.dto;

public record PatientResponseDTO(
        String id,
        String name,
        String address,
        String email,
        String dateOfBirth
) {
}
