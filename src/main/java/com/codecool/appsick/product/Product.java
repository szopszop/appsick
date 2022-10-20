package com.codecool.appsick.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Product {

    private UUID productId;
    private String productName;
    private int amount;
    private boolean drugCoverage;

}
