package org.eu.appsick.order;

import lombok.Data;
import org.eu.appsick.product.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {

    private Long orderId;
    private LocalDate orderDate;
    private Long patientId;
    private Long prescriptionId;
    private List<Product> productList;
    private String deliveryAddress;

    public Order(Long orderId, LocalDate orderDate, Long patientId, Long prescriptionId, String deliveryAddress) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.patientId = patientId;
        this.prescriptionId = prescriptionId;
        this.deliveryAddress = deliveryAddress;
        this.productList = new ArrayList<>();
    }
}
