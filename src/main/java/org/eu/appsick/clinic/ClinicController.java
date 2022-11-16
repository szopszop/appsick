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
public class ClinicController {

    private ClinicService clinicService;
    private DoctorService doctorService;

    @Autowired
    public ClinicController(ClinicService clinicService, DoctorService doctorService) {
        this.clinicService = clinicService;
        this.doctorService = doctorService;
    }

    @GetMapping("/{clinicId}")
    public Optional<Clinic> getClinicById(@PathVariable long clinicId) {
        return clinicService.getClinicById(clinicId);
    }

    // TODO: implement new methods, this is a dummy function
    @GetMapping("/{clinicId}/doctor")
    public List<Doctor> getDoctorsByClinic(@PathVariable long clinicId) {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctorService.getDoctorById(1).orElse(new Doctor()));
        return doctors;
    }
}
