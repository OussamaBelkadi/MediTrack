package os.patientservice.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import os.patientservice.dto.PatientRequestDTO;
import os.patientservice.dto.PatientResponseDTO;
import os.patientservice.service.PatientService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Endpoint to get all patients.
     *
     * @return List of PatientResponseDTO
     */
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    /**
     * Endpoint to add a new patient.
     *
     * @param patientRequestDTO PatientRequestDTO
     * @return PatientResponseDTO
     */
    @PostMapping
    public ResponseEntity<PatientResponseDTO> addPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.addPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathParam(value = "id") UUID id, @RequestBody PatientRequestDTO patientRequestDTO ) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }
}
