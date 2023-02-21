package org.eu.appsick.user.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
@CrossOrigin(origins = {"http://localhost:3000/", "https://appsick.eu.org"}, allowedHeaders = "*", allowCredentials = "true")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id,
                                            @RequestParam(required = false, name = "user_id") boolean isUserId) {
        if (isUserId) return patientService.getPatientByUserId(id);
        return patientService.getPatientById(id);
    }

}
