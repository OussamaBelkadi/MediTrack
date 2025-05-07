package os.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import os.patientservice.dto.validationgroup.ValidationGroup;

public record PatientRequestDTO(
       @NotBlank @Size(max = 100, message = "Name can not exceed 100 characters") String name,
       @NotBlank @Email(message = "Email should be valid") String email,
       @NotBlank(message = "Address is required") String address,
       String dateOfBirth,
       @NotBlank(groups = ValidationGroup.Create.class, message = "Register date is required") String registeredDate
) {
}
