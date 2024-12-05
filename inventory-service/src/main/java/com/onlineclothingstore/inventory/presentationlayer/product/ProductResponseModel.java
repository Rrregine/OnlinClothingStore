package com.onlineclothingstore.inventory.presentationlayer.product;

import com.onlineclothingstore.inventory.dataaccesslayer.product.Manufacturer;
import com.onlineclothingstore.inventory.dataaccesslayer.product.Options;
import com.onlineclothingstore.inventory.dataaccesslayer.product.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductResponseModel {

    private String productId;
    private String inventoryId;
    private String size;
    private String model;
    private String image;
    private Status status;
    private List<Options> options;
//    private String country;
//    private String brand;
}
