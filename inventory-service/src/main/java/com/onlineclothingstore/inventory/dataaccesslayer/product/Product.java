package com.onlineclothingstore.inventory.dataaccesslayer.product;

import com.onlineclothingstore.inventory.dataaccesslayer.inventory.InventoryIdentifier;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private ProductIdentifier productIdentifier;

    @Embedded
    private InventoryIdentifier inventoryIdentifier;

    private String size;
    private String model;
    private String image;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_options", joinColumns = @JoinColumn(name = "product_id"))
    private List<Options> options;

    @Embedded
    private Manufacturer manufacturer;

    /*public Product(ProductIdentifier productIdentifier, InventoryIdentifier inventoryIdentifier,
                   String size, String model, String image, Status status, List<Options> options,
                   Manufacturer manufacturer) {
        this.productIdentifier = productIdentifier;
        this.inventoryIdentifier = inventoryIdentifier;
        this.size = size;
        this.model = model;
        this.image = image;
        this.status = status;
        this.options = options;
        this.manufacturer = manufacturer;
    }*/
}
