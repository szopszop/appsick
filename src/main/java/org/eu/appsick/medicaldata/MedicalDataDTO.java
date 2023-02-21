package org.eu.appsick.medicaldata;

import lombok.Data;

@Data
public class MedicalDataDTO {

    private int weight;
    private int height;
    private String medicalConditions;
    private String allergies;
    private String addictions;
    private String medicaments;
    private String recommendations;
    private int userId;
    private int VisitId;
}
