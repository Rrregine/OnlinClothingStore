package com.onlineclothingstore.employees.dataaccesslayer.employee;

import com.onlineclothingstore.employees.dataaccesslayer.employee.EmployeeIdentifier;
import com.onlineclothingstore.employees.dataaccesslayer.department.DepartmentIdentifier;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private EmployeeIdentifier employeeIdentifier;

    @Embedded
    private DepartmentIdentifier departmentIdentifier;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private Double salary;
    private Double commissionRate;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "employee_phonenumbers", joinColumns = @JoinColumn(name = "employee_id"))
    private List<PhoneNumber> phoneNumbers;

    /*
    public Employee(@NotNull List<PhoneNumber> phoneNumbers, @NotNull String firstName, @NotNull String lastName, @NotNull String email, @NotNull Double salary, @NotNull Double commissionRate) {
        this.employeeIdentifier = new EmployeeIdentifier();
        this.phoneNumbers = phoneNumbers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = email;
        this.salary = salary;
        this.commissionRate = commissionRate;
    } */
}