package com.onlineclothingstore.inventory.dataaccesslayer.product;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Embeddable
@Getter
public class Manufacturer {

    private String brand;
    private String country;

    public Manufacturer(@NotNull String brand, @NotNull String country) {
        this.brand = brand;
        this.country = country;
    }

    public Manufacturer() {

    }
}
