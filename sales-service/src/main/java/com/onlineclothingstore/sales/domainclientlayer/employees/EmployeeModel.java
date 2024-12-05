package com.onlineclothingstore.sales.domainclientlayer.employees;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class EmployeeModel {

    String employeeId;
    String departmentId;
    String firstName;
    String lastName;
}
