package org.eu.appsick.medicaldata;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
class MedicalData {

    private Long medicalDataId;
    private Long patientId;
    private int weight;
    private int height;
    private String medicalConditions;
    private String allergies;
    private String addictions;
    private String medicaments;
}
