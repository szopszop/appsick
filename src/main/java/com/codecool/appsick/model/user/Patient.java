package com.codecool.appsick.model.user;

import java.util.UUID;

public class Patient extends User {

    private UUID patientId;
    private UUID medicalDataId;

    public Patient(String email, String password) {
        super(email, password);
    }
}
