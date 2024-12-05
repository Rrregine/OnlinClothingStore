package com.onlineclothingstore.inventory.dataaccesslayer.inventory;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class InventoryIdentifier {

    String inventoryId;

    public InventoryIdentifier(String inventoryId){
            this.inventoryId = inventoryId;
    }

    public InventoryIdentifier() {
        this.inventoryId = UUID.randomUUID().toString();
    }
}
