package org.eu.appsick.visit;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/visit")
public class VisitController {

    private final VisitService visitService;
    private final Gson gson = new Gson();

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping(value = "/{visit_id}", produces = "application/json")
    public String getVisit(@PathVariable String visit_id) {
        return visitService.getById(visit_id).toString();
    }

    @GetMapping(value = "/doctor/{doctor_id}")
    public String getDoctorVisits(@PathVariable String doctor_id) {
        return visitService.getDoctorVisits(doctor_id).toString();
    }

    @GetMapping(value = "/patient/{patient_id}")
    public String getVisits(@PathVariable String patient_id) {
        return visitService.getPatientVisits(patient_id).toString();
    }

    @PostMapping()
    public ResponseEntity<String> postVisit(@RequestBody String body) {
        Visit newVisit = gson.fromJson(body, Visit.class);
        visitService.addVisit(newVisit);
        return new ResponseEntity<>("Visit successfully added to database", HttpStatus.OK);
    }

    @PatchMapping(value = "/{visit_id}")
    public ResponseEntity<String> patchVisit(@PathVariable String visit_id, @RequestBody String body) {
        Visit editedVisit = gson.fromJson(body, Visit.class);
        return (visitService.editVisit(editedVisit.getVisitId(), editedVisit)) ?
                new ResponseEntity<>("Visit successfully updated", HttpStatus.OK) :
                new ResponseEntity<>("Visit doesn't exist", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{visit_id}")
    public ResponseEntity<String> deleteVisit(@PathVariable String visit_id) {
        visitService.deleteVisit(UUID.fromString(visit_id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
