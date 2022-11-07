package org.eu.appsick.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/visit")
public class VisitController {

    private final VisitService myVisitService;

    @Autowired
    public VisitController(VisitService myVisitService) {
        this.myVisitService = myVisitService;
    }

    @GetMapping(value = "/{visitId}", produces = "application/json")
    public String getVisit(@PathVariable long visitId) {
        return myVisitService.getById(visitId).toString();
    }

    @GetMapping(value = "/doctor/{doctorId}")
    public String getDoctorVisits(@PathVariable long doctorId) {
        return myVisitService.getDoctorVisits(doctorId).toString();
    }

    @GetMapping(value = "/patient/{patientId}")
    public String getVisits(@PathVariable long patientId) {
        return myVisitService.getPatientVisits(patientId).toString();
    }

    @PostMapping()
    public ResponseEntity<String> postVisit(@RequestBody Visit newVisit) {
        myVisitService.addVisit(newVisit);
        return new ResponseEntity<>("Visit successfully added to database with visitId: " + newVisit.getVisitId(), HttpStatus.OK);
    }

    @PatchMapping(value = "/{visitId}")
    public ResponseEntity<String> patchVisit(@PathVariable long visitId, @RequestBody Visit editedVisit) {
        return (myVisitService.editVisit(visitId, editedVisit)) ?
                new ResponseEntity<>("Visit successfully updated", HttpStatus.OK) :
                new ResponseEntity<>("Visit doesn't exist", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{visitId}")
    public ResponseEntity<String> deleteVisit(@PathVariable long visitId) {
        myVisitService.deleteVisit(visitId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
