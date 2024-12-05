package com.onlineclothingstore.inventory.presentationlayer.product;

import com.onlineclothingstore.inventory.dataaccesslayer.product.Manufacturer;
import com.onlineclothingstore.inventory.dataaccesslayer.product.Options;
import com.onlineclothingstore.inventory.dataaccesslayer.product.Status;
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
    private Status status;
//    private String brand;
//    private String country;
    private List<Options> options;
}
