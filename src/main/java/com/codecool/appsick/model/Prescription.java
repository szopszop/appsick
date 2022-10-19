package com.codecool.appsick.model;

import com.codecool.appsick.model.user.Doctor;
import com.codecool.appsick.model.user.Patient;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Prescription {

    private UUID prescriptionId;
    private Patient patient;
    private Doctor doctor;
    private LocalDate prescriptionDate;
    private int prescriptionCode;
    private List<Product> productList;

    public Prescription(UUID prescriptionId, Patient patient, Doctor doctor, LocalDate prescriptionDate, int prescriptionCode) {
        this.prescriptionId = prescriptionId;
        this.patient = patient;
        this.doctor = doctor;
        this.prescriptionDate = prescriptionDate;
        this.prescriptionCode = prescriptionCode;
        this.productList = new ArrayList<>();
    }
}
