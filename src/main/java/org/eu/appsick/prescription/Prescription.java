package org.eu.appsick.prescription;

import org.eu.appsick.product.Product;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Prescription {

    private UUID prescriptionId;
    private UUID patientId;
    private UUID doctorId;
    private LocalDate prescriptionDate;
    private int prescriptionCode;
    private List<Product> productList;

    public Prescription(UUID prescriptionId, UUID patientId, UUID doctorId, LocalDate prescriptionDate, int prescriptionCode) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.prescriptionDate = prescriptionDate;
        this.prescriptionCode = prescriptionCode;
        this.productList = new ArrayList<>();
    }
}
