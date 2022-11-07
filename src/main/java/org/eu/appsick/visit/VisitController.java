package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.clinic.ClinicRepository;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/visit")
public class VisitController {

    private final VisitService myVisitService;
    private final ClinicRepository clinicRepository;

    @Autowired
    public VisitController(VisitService myVisitService, ClinicRepository clinicRepository) {
        this.myVisitService = myVisitService;
        this.clinicRepository = clinicRepository;
    }

    @GetMapping()
    public String getAllVisits() {
        Visit visit = new Visit(
                2,
                null,
                null,
                clinicRepository.findByClinicId(1).get(),
                LocalDateTime.now(),
                true,
                "boli",
                Visit.VisitStatus.PENDING);
        myVisitService.addVisit(visit);
        return "Done";
    }

    @GetMapping(value = "/{visitId}", produces = "application/json")
    public Optional<Visit> getVisit(@PathVariable long visitId) {
        return myVisitService.getById(visitId);
    }

    @GetMapping(value = "/doctor/{doctorId}")
    public List<Visit> getDoctorVisits(@PathVariable long doctorId) {
        return myVisitService.getDoctorVisits(doctorId);
    }

    @GetMapping(value = "/patient/{patientId}")
    public List<Visit> getVisits(@PathVariable long patientId) {
        return myVisitService.getPatientVisits(patientId);
    }

    @PostMapping()
    public ResponseEntity<Visit> postVisit(@RequestBody Visit newVisit) {
        myVisitService.addVisit(newVisit);
        return new ResponseEntity<>(newVisit, HttpStatus.OK);
    }

    @PatchMapping(value = "/{visitId}")
    public ResponseEntity<Visit> patchVisit(@PathVariable long visitId, @RequestBody Visit editedVisit) {
        return (myVisitService.editVisit(visitId, editedVisit)) ?
                new ResponseEntity<>(editedVisit, HttpStatus.OK) :
                new ResponseEntity<>(editedVisit, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{visitId}")
    public ResponseEntity<String> deleteVisit(@PathVariable long visitId) {
        return (myVisitService.deleteVisit(visitId)) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
