package com.codecool.appsick.medicaldata;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class MedicalData {

    private UUID medicalDataId;
    private UUID patientId;
    private int weight;
    private int height;
    private String medicalConditions;
    private String allergies;
    private String addictions;
    private String medicaments;
}
