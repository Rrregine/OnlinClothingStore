package com.onlineclothingstore.apigateway.presentationlayer.sales;

import com.onlineclothingstore.apigateway.domainclientlayer.sales.SalesStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestModel {

    String inventoryId;
    String productId;
    String employeeId;
    String productSize;
    String model;
    String productImage;
    String clientUsername;
    String employeeFirstName;
    String employeeLastName;
    SalesStatus salesStatus;
}