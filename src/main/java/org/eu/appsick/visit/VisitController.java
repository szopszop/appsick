package org.eu.appsick.visit;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visit")
public class VisitController {

    // TODO: dependency injection
    private final VisitDao visitDao = new VisitDaoOrm();
    @GetMapping(value = "/{visit_id}", produces = "application/json")
    public String getVisit(@PathVariable String visit_id){
        return null;
    }
    @PostMapping("/patient/{patient_id}")
    public String postVisit(@PathVariable String patient_id){
        return null;
    }

    @PutMapping("/{visit_id}")
    public String putVisit(@PathVariable String visit_id){
        return null;
    }

    @DeleteMapping("/{visit_id}")
    public String deleteVisit(@PathVariable String visit_id){
        return null;
    }
}
