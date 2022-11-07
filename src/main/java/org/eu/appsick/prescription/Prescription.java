package org.eu.appsick.prescription;

import org.eu.appsick.product.Product;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Prescription {

    private long prescriptionId;
    private long patientId;
    private long doctorId;
    private LocalDate prescriptionDate;
    private int prescriptionCode;
    private List<Product> productList;

    public Prescription(long prescriptionId, long patientId, long doctorId, LocalDate prescriptionDate, int prescriptionCode) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.prescriptionDate = prescriptionDate;
        this.prescriptionCode = prescriptionCode;
        this.productList = new ArrayList<>();
    }
}
