package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.clinic.ClinicService;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.doctor.DoctorService;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.user.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/visit")
@CrossOrigin(origins = "http://localhost:3000")
public class VisitController {

    private final VisitService visitService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final ClinicService clinicService;

    @Autowired
    public VisitController(VisitService visitService, DoctorService doctorService, PatientService patientService, ClinicService clinicService) {
        this.visitService = visitService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.clinicService = clinicService;
    }

    @GetMapping(value = "/{visitId}", produces = "application/json")
    public Optional<Visit> getVisit(@PathVariable long visitId) {
        return visitService.getVisitById(visitId);
    }

    @GetMapping(value = "/doctor/{doctorId}")
    public List<Visit> getDoctorVisits(@PathVariable long doctorId) {
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
        if (doctor.isPresent()) {
            return visitService.getDoctorVisits(doctor.get());
        }
        else return new ArrayList<>();
    }

    @GetMapping(value = "/patient/{patientId}")
    public List<Visit> getPatientVisits(@PathVariable long patientId) {
        Optional<Patient> patient = patientService.getPatientById(patientId);
        if (patient.isPresent()) {
            return visitService.getPatientVisits(patient.get());
        }
        else return new ArrayList<>();
    }

    @GetMapping(value = "/patient/{patientId}/future")
    public List<Visit> getFutureVisits(@PathVariable long patientId) {
        Optional<Patient> patient = patientService.getPatientById(patientId);
        if (patient.isPresent()) {
            return visitService.getFutureVisitsByPatient(patient.get());
        }
        else return new ArrayList<>();
    }


    @GetMapping(value = "/patient/{patientId}/current")
    public List<Visit> getCurrentVisits(@PathVariable long patientId) {
        Optional<Patient> patient = patientService.getPatientById(patientId);


        if (patient.isPresent()) {
            return visitService.getCurrentVisitsByPatient(patient.get().getPatientId());
        }
        else return new ArrayList<>();
    }

    @GetMapping(value = "/patient/{patientId}/past")
    public List<Visit> getPastVisits(@PathVariable long patientId) {
        Optional<Patient> patient = patientService.getPatientById(patientId);
        if (patient.isPresent()) {
            return visitService.getPastVisitsByPatient(patient.get(), 0);
        }
        else return new ArrayList<>();
    }

    @GetMapping(value = "/clinic/{clinicId}")
    public List<Visit> getClinicVisits(@PathVariable long clinicId) {
        Optional<Clinic> clinic = clinicService.getClinicById(clinicId);
        if (clinic.isPresent()) {
            return visitService.getClinicVisits(clinic.get());
        }
        else return new ArrayList<>();
    }

    @PostMapping()
    public ResponseEntity<Visit> postVisit(@RequestBody Visit newVisit) {
        visitService.addVisit(newVisit);
        return new ResponseEntity<>(newVisit, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{visitId}")
    public ResponseEntity<Visit> patchVisit(@PathVariable long visitId, @RequestBody Visit editedVisit) {
        return (visitService.editVisit(visitId, editedVisit)) ?
                new ResponseEntity<>(editedVisit, HttpStatus.OK) :
                new ResponseEntity<>(editedVisit, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{visitId}")
    public ResponseEntity<Visit> deleteVisit(@PathVariable long visitId) {
        visitService.deleteVisit(visitId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
