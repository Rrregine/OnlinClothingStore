package com.onlineclothingstore.sales.domainclientlayer.inventory;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Manufacturer {

    private String brand;
    private String country;

   /* public Manufacturer(@NotNull String brand, @NotNull String country) {
        this.brand = brand;
        this.country = country;
    }

    public Manufacturer() {

    }*/
}
