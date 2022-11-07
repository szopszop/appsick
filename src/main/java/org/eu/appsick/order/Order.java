package org.eu.appsick.order;

import org.eu.appsick.prescription.Prescription;
import org.eu.appsick.product.Product;
import org.eu.appsick.user.patient.Patient;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Order {

    private UUID orderId;
    private LocalDate orderDate;
    private UUID patientId;
    private UUID prescriptionId;
    private List<Product> productList;
    private String deliveryAddress;

    public Order(UUID orderId, LocalDate orderDate, UUID patientId, UUID prescriptionId, String deliveryAddress) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.patientId = patientId;
        this.prescriptionId = prescriptionId;
        this.deliveryAddress = deliveryAddress;
        this.productList = new ArrayList<>();
    }
}
