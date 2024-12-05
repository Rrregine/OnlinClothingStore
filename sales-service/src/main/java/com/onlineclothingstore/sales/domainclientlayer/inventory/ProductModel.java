package com.onlineclothingstore.sales.domainclientlayer.inventory;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class ProductModel {

    String productId;
    String inventoryId;
    String size;
    String model;
    String image;
    Status status;
//    String brand;
//    String country;
    List<Options> optionsList;
}
