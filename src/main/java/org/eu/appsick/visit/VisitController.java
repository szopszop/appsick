package org.eu.appsick.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/visit")
public class VisitController {

    private final VisitService visitService;
    @Autowired
    public VisitController(VisitService visitService){
        this.visitService = visitService;
    }

    @GetMapping(value = "/{visit_id}", produces = "application/json")
    public String getVisit(@PathVariable String visit_id){
        return visitService.getById(visit_id).toString();
    }

    @GetMapping(value = "/doctor/{doctor_id}")
    public String getDoctorVisits(@PathVariable String doctor_id){
        return visitService.getDoctorVisits(doctor_id).toString();
    }

    @GetMapping(value = "/patient/{patient_id}")
    public String getVisits(@PathVariable String patient_id){
        return visitService.getPatientVisits(patient_id).toString();
    }

    // TODO: ogarnąć ustawianie HttpStatus
    @PostMapping()
    public String postVisit(@RequestParam Map<String, String> params){
        return null;
    }

    @PatchMapping(value = "/{visit_id}")
    public String patchVisit(@PathVariable String visit_id){
        return null;
    }

    @DeleteMapping(value = "/{visit_id}")
    public String deleteVisit(@PathVariable String visit_id){
        return null;
    }
}
