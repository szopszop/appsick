package com.codecool.appsick.clinic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClinicController {

    @GetMapping("/clinic")
    public String getClinics() {
        return "clinic";
    }
}
