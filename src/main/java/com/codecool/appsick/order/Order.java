package com.codecool.appsick.order;

import com.codecool.appsick.prescription.Prescription;
import com.codecool.appsick.product.Product;
import com.codecool.appsick.user.patient.Patient;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Order {

    private UUID orderId;
    private LocalDate orderDate;
    private Patient patient;
    private Prescription prescription;
    private List<Product> productList;
    private String deliveryAddress;

    public Order(UUID orderId, LocalDate orderDate, Patient patient, Prescription prescription, String deliveryAddress) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.patient = patient;
        this.prescription = prescription;
        this.deliveryAddress = deliveryAddress;
        this.productList = new ArrayList<>();
    }
}
