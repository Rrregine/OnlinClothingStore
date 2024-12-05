package com.onlineclothingstore.apigateway.presentationlayer.employees.employees;

import com.onlineclothingstore.apigateway.domainclientlayer.clients.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestModel {
    private String employeeId;
    private String departmentId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Double salary;
    private Double commissionRate;
    private List<PhoneNumber> phoneNumbers;
}
