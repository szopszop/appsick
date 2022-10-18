package com.codecool.appsick.model.user;

import java.util.UUID;

public class Patient extends User {

    private UUID medicalDataId;
    private UUID userId;

    public Patient(String email, String password) {
        super(email, password);
    }
}
