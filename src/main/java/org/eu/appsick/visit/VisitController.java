package org.eu.appsick.visit;

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

    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    //only for testing TODO: delete
    @GetMapping()
    public String addNewVisitTest() {
        Visit visit = new Visit(
                2,
                1,
                1,
                1,
                LocalDateTime.now(),
                true,
                "boli",
                Visit.VisitStatus.PENDING);
        visitService.addVisit(visit);
        return "Done";
    }

    @GetMapping(value = "/{visitId}", produces = "application/json")
    public Optional<Visit> getVisit(@PathVariable long visitId) {
        return visitService.getVisitById(visitId);
    }

    @GetMapping(value = "/doctor/{doctorId}")
    public List<Visit> getDoctorVisits(@PathVariable long doctorId) {
        return visitService.getDoctorVisits(doctorId);
    }

    @GetMapping(value = "/patient/{patientId}")
    public List<Visit> getVisits(@PathVariable long patientId) {
        return visitService.getPatientVisits(patientId);
    }

    @PostMapping()
    public ResponseEntity<Visit> postVisit(@RequestBody Visit newVisit) {
        visitService.addVisit(newVisit);
        return new ResponseEntity<>(newVisit, HttpStatus.OK);
    }

    @PatchMapping(value = "/{visitId}")
    public ResponseEntity<Visit> patchVisit(@PathVariable long visitId, @RequestBody Visit editedVisit) {
        return (visitService.editVisit(visitId, editedVisit)) ?
                new ResponseEntity<>(editedVisit, HttpStatus.OK) :
                new ResponseEntity<>(editedVisit, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{visitId}")
    public ResponseEntity<String> deleteVisit(@PathVariable long visitId) {
        return (visitService.deleteVisit(visitId)) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
