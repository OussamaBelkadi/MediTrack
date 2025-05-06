package os.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PatientRequestDTO(
       @NotBlank @Size(max = 100, message = "Name can not exceed 100 characters") String name,
       @NotBlank @Email(message = "Email should be valid") String email,
       @NotBlank(message = "Address is required") String address,
       String dateOfBirth,
       String registeredDate
) {
}
