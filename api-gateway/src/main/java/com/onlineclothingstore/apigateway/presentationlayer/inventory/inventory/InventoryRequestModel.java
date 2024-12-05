package com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestModel {

    private String type;
}
