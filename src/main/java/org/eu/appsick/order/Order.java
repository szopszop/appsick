package org.eu.appsick.order;

import lombok.Data;
import org.eu.appsick.product.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {

    private long orderId;
    private LocalDate orderDate;
    private long patientId;
    private long prescriptionId;
    private List<Product> productList;
    private String deliveryAddress;

    public Order(long orderId, LocalDate orderDate, long patientId, long prescriptionId, String deliveryAddress) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.patientId = patientId;
        this.prescriptionId = prescriptionId;
        this.deliveryAddress = deliveryAddress;
        this.productList = new ArrayList<>();
    }
}
