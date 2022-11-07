package org.eu.appsick.medicaldata;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
class MedicalData {

    private long medicalDataId;
    private long patientId;
    private int weight;
    private int height;
    private String medicalConditions;
    private String allergies;
    private String addictions;
    private String medicaments;
}
