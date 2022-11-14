package org.eu.appsick.clinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clinic")
public class ClinicController {

    private ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/{clinicId}")
    public Optional<Clinic> getClinicById(@PathVariable long clinicId) {
        return clinicService.getClinicById(clinicId);
    }
}
