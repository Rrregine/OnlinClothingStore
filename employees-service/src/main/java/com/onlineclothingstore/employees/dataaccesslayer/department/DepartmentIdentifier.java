package com.onlineclothingstore.employees.dataaccesslayer.department;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class DepartmentIdentifier {

    private String departmentId;

    public DepartmentIdentifier() {
        this.departmentId = UUID.randomUUID().toString();
    }

    public DepartmentIdentifier(String departmentId) {
        this.departmentId = departmentId;
    }
}
