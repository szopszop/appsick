package org.eu.appsick.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/visit")
public class VisitController {

    private final VisitService myVisitService;

    @Autowired
    public VisitController(VisitService myVisitService) {
        this.myVisitService = myVisitService;
    }

    @GetMapping()
    public List<Visit> getAllVisits() {
        return myVisitService.getAllVisits();
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
