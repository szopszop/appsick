package org.eu.appsick.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/visit")
public class VisitController {

    private final MyVisitService myVisitService;

    @Autowired
    public VisitController(MyVisitService myVisitService) {
        this.myVisitService = myVisitService;
    }

    @GetMapping(value = "/{visit_id}", produces = "application/json")
    public String getVisit(@PathVariable UUID visit_id) {
        return myVisitService.getById(visit_id).toString();
    }

    @GetMapping(value = "/doctor/{doctor_id}")
    public String getDoctorVisits(@PathVariable UUID doctor_id) {
        return myVisitService.getDoctorVisits(doctor_id).toString();
    }

    @GetMapping(value = "/patient/{patient_id}")
    public String getVisits(@PathVariable UUID patient_id) {
        return myVisitService.getPatientVisits(patient_id).toString();
    }

    @PostMapping()
    public ResponseEntity<String> postVisit(@RequestBody Visit newVisit) {
        myVisitService.addVisit(newVisit);
        return new ResponseEntity<>("Visit successfully added to database with visitId: " + newVisit.getVisitId(), HttpStatus.OK);
    }

    @PatchMapping(value = "/{visit_id}")
    public ResponseEntity<String> patchVisit(@PathVariable String visit_id, @RequestBody Visit editedVisit) {
        return (myVisitService.editVisit(UUID.fromString(visit_id), editedVisit)) ?
                new ResponseEntity<>("Visit successfully updated", HttpStatus.OK) :
                new ResponseEntity<>("Visit doesn't exist", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{visit_id}")
    public ResponseEntity<String> deleteVisit(@PathVariable String visit_id) {
        myVisitService.deleteVisit(UUID.fromString(visit_id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
