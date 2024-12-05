package com.onlineclothingstore.employees.dataaccesslayer.department;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
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
