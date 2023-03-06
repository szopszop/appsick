package org.eu.appsick.prescription;

import org.eu.appsick.product.Product;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Prescription {

    private Long prescriptionId;
    private Long patientId;
    private Long doctorId;
    private LocalDate prescriptionDate;
    private int prescriptionCode;
    private List<Product> productList;

    public Prescription(Long prescriptionId, Long patientId, Long doctorId, LocalDate prescriptionDate, int prescriptionCode) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.prescriptionDate = prescriptionDate;
        this.prescriptionCode = prescriptionCode;
        this.productList = new ArrayList<>();
    }
}
