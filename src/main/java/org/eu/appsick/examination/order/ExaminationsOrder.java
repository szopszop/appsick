package org.eu.appsick.examination.order;

import org.eu.appsick.examination.Examination;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
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
