package com.codecool.appsick.model;

import java.util.UUID;

public class MedicalData {

    private UUID medicalDataId;
    private UUID patientId;
    private int weight;
    private int height;
    private String medicalConditions;
    private String allergies;
    private String addictions;
    private String medicaments;

    public MedicalData(UUID patientId, int weight, int height, String medicalConditions, String allergies, String addictions, String medicaments) {
        this.medicalDataId = UUID.randomUUID();
        this.patientId = patientId;
        this.weight = weight;
        this.height = height;
        this.medicalConditions = medicalConditions;
        this.allergies = allergies;
        this.addictions = addictions;
        this.medicaments = medicaments;
    }
}
