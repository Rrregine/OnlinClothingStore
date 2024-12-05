package com.onlineclothingstore.sales.presentationlayer;

import com.onlineclothingstore.sales.dataaccesslayer.SalesStatus;
import com.onlineclothingstore.sales.domainclientlayer.inventory.Options;
import com.onlineclothingstore.sales.domainclientlayer.inventory.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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