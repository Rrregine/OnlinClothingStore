package com.onlineclothingstore.apigateway.domainclientlayer.inventory.products;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Options {

    private String name;
    private String description;
    private Double price;

   /* public Options(@NotNull String name, @NotNull String description, @NotNull Double price) {
        Objects.requireNonNull(this.name = name);
        Objects.requireNonNull(this.description = description);
        Objects.requireNonNull(this.price = price);
    }

    public Options() {

    } */
}

