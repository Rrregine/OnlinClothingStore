package com.onlineclothingstore.apigateway.presentationlayer.inventory.product;


import com.onlineclothingstore.apigateway.domainclientlayer.inventory.products.Manufacturer;
import com.onlineclothingstore.apigateway.domainclientlayer.inventory.products.Options;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestModel {

    private String productId;
    private String inventoryId;
    private String size;
    private String model;
    private String image;
    private String status;
    private List<Options> options;
//    private String brand;
//    private String country;
}
