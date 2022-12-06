package org.eu.appsick.user.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
@CrossOrigin(origins = "https://appsick.eu.org", allowedHeaders = "*", allowCredentials = "true")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{patientId}")
    public Optional<Patient> getPatientById(@PathVariable Long patientId) {
        return patientService.getPatientById(patientId);
    }
}
