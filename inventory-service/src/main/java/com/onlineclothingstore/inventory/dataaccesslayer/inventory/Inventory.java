package com.onlineclothingstore.inventory.dataaccesslayer.inventory;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inventories")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private InventoryIdentifier inventoryIdentifier;

    private String type;

    public Inventory() {
        this.inventoryIdentifier = new InventoryIdentifier();
    }

    public Inventory(String type) {
        this.inventoryIdentifier = new InventoryIdentifier();
        this.type = type;
    }
}
