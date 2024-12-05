package com.onlineclothingstore.apigateway.domainclientlayer.inventory.products;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {

    private String brand;
    private String country;

}
