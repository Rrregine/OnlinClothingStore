package com.onlineclothingstore.apigateway.presentationlayer.employees.employees;

import com.onlineclothingstore.apigateway.domainclientlayer.clients.PhoneNumber;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseModel  extends RepresentationModel<EmployeeResponseModel> {

    private String employeeId;
    private String departmentId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Double salary;
    private Double commissionRate;
    private List<PhoneNumber> phoneNumbers;
}
