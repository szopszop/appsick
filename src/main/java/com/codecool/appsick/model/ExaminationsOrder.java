package com.codecool.appsick.model;

import com.codecool.appsick.model.user.Doctor;
import com.codecool.appsick.model.user.Patient;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ExaminationsOrder {

    private UUID examinationsOrderId;
    private String examinationsOrderName;
    private Patient patient;
    private Doctor doctor;
    private boolean completed;
    private LocalDate examinationsOrderDate;
    private List<Examination> examinations;

    public ExaminationsOrder(UUID examinationsOrderId, Patient patient, Doctor doctor, boolean completed, LocalDate examinationsOrderDate) {
        this.examinationsOrderId = examinationsOrderId;
        this.patient = patient;
        this.doctor = doctor;
        this.completed = completed;
        this.examinationsOrderDate = examinationsOrderDate;
        this.examinations = new ArrayList<>();
    }
}
