package org.eu.appsick.clinic;

import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clinic")
@CrossOrigin(origins = {"http://localhost:3000/", "https://appsick.eu.org"}, allowedHeaders = "*", allowCredentials = "true")
public class ClinicController {

    private final ClinicService clinicService;
    private final DoctorService doctorService;

    @Autowired
    public ClinicController(ClinicService clinicService, DoctorService doctorService) {
        this.clinicService = clinicService;
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Clinic> getAllClinics(){
        return clinicService.getAllClinics();
    }

    @GetMapping("/{clinicId}")
    public Optional<Clinic> getClinicById(@PathVariable Long clinicId) {
        return clinicService.getClinicById(clinicId);
    }

    @GetMapping("/{clinicId}/doctor")
    public List<Doctor> getDoctorsByClinic(@PathVariable Long clinicId) {
        return doctorService.getDoctorsByClinic(clinicId);
    }

}
