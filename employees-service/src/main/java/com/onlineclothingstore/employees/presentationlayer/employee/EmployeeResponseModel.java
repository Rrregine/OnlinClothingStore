package com.onlineclothingstore.employees.presentationlayer.employee;

import com.onlineclothingstore.employees.dataaccesslayer.employee.PhoneNumber;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseModel {

    private String employeeId;
    private String departmentId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Double salary;
    private Double commissionRate;
    private List<PhoneNumber> phoneNumbers;
}
