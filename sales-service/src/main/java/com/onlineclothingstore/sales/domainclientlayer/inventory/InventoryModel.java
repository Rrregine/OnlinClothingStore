package com.onlineclothingstore.sales.domainclientlayer.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class InventoryModel {
    String inventoryId;
    String type;
}
