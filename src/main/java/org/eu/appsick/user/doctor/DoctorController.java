package org.eu.appsick.user.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctor")
@CrossOrigin(origins = {"http://localhost:3000/", "https://appsick.eu.org"}, allowedHeaders = "*", allowCredentials = "true")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{doctorId}")
    public Optional<Doctor> getDoctorById(@PathVariable Long doctorId) {
        return doctorService.getDoctorById(doctorId);
    }

    @GetMapping("/specialities")
    public List<Doctor.Speciality> getDoctorSpecialities() {
        return Arrays.asList(Doctor.Speciality.values());
    }

    @GetMapping("/specialities/{speciality}")
    public List<Doctor> getDoctorsBySpeciality(@PathVariable("speciality") String specialityName) {
        Doctor.Speciality speciality = Doctor.Speciality.fromValue(specialityName);
        return doctorService.getDoctorsBySpeciality(speciality); }
}
