package com.onlineclothingstore.employees.dataaccesslayer.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmployeeIdentifier_EmployeeId(String employeeId);

    List<Employee> findAllByDepartmentIdentifier_DepartmentId(String departmentId);
}