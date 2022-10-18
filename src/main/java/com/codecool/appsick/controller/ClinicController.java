package com.codecool.appsick.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClinicController {

    @GetMapping("/clinic")
    public String getClinics() {
        return "clinic";
    }
}
