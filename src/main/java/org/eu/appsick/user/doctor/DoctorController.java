package org.eu.appsick.user.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctor")
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
}
