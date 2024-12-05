package com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory;

import com.onlineclothingstore.apigateway.presentationlayer.employees.employees.EmployeeResponseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class InventoryResponseModel extends RepresentationModel<InventoryResponseModel> {

    String inventoryId;
    String type;
}
