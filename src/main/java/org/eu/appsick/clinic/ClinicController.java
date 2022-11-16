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
@CrossOrigin(origins = "http://localhost:3000")
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

    // TODO: implement new methods, these use dummy functions
    @GetMapping
    public List<Clinic> getAllClinics(){
        return dummyClinicFunction();
    }

    @GetMapping("/{clinicId}/doctor")
    public List<Doctor> getDoctorsByClinic(@PathVariable long clinicId) {
        return clinicId != 0 ? dummyDoctorFunction() : new ArrayList<>();
    }

    private List<Doctor> dummyDoctorFunction(){
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctorService.getDoctorById(1).orElse(new Doctor()));
        doctors.add(doctorService.getDoctorById(2).orElse(new Doctor()));
        return doctors;
    }
    private List<Clinic> dummyClinicFunction(){
        List<Clinic> clinics = new ArrayList<>();
        clinics.add(clinicService.getClinicById(1).orElse(new Clinic()));
        clinics.add(clinicService.getClinicById(2).orElse(new Clinic()));
        return clinics;
    }
}
