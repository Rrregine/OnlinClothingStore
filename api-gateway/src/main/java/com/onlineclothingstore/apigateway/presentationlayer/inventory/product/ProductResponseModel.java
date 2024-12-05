package com.onlineclothingstore.apigateway.presentationlayer.inventory.product;

import com.onlineclothingstore.apigateway.domainclientlayer.inventory.products.Manufacturer;
import com.onlineclothingstore.apigateway.domainclientlayer.inventory.products.Options;
import com.onlineclothingstore.apigateway.presentationlayer.employees.employees.EmployeeResponseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ProductResponseModel extends RepresentationModel<ProductResponseModel> {

    String productId;
    String inventoryId;
    String size;
    String model;
    String image;
    String status;
    List<Options> options;
//    String country;
//    String brand;
}
