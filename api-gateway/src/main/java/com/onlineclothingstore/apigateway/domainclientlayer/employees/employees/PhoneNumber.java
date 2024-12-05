package com.onlineclothingstore.apigateway.domainclientlayer.employees.employees;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class PhoneNumber {

    private PhoneType type;
    private String number;

   /* public PhoneNumber(@NotNull PhoneType type, @NotNull String number) {
        this.type = type;
        this.number = number;
    }*/
}
