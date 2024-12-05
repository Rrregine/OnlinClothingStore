package com.onlineclothingstore.apigateway.domainclientlayer.employees.departments;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Position {

    private String title;
    private String code;

    /*
    public Position(String title, String code) {
        this.code = code;
        this.title = title;
    } */
}
