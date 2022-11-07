package org.eu.appsick.product;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class Product {

    private long productId;
    private String productName;
    private int amount;
    private boolean drugCoverage;

}
