package com.onlineclothingstore.sales.domainclientlayer.inventory;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
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

    }*/
}

