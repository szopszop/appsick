package org.eu.appsick.visit;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visit")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService){
        this.visitService = visitService;
    }

    @GetMapping(value = "/{visit_id}", produces = "application/json")
    public String getVisit(@PathVariable String visit_id){
        return null;
    }

    @PostMapping(value = "/patient/{patient_id}", produces = "application/json")
    public String postVisit(@PathVariable String patient_id){
        return null;
    }

    @PutMapping(value = "/{visit_id}", produces = "application/json")
    public String putVisit(@PathVariable String visit_id){
        return null;
    }

    @DeleteMapping(value = "/{visit_id}", produces = "application/json")
    public String deleteVisit(@PathVariable String visit_id){
        return null;
    }
}
