package com.onlineclothingstore.employees.mapperlayer.employee;

import com.onlineclothingstore.employees.dataaccesslayer.employee.EmployeeIdentifier;
import com.onlineclothingstore.employees.dataaccesslayer.department.DepartmentIdentifier;
import com.onlineclothingstore.employees.dataaccesslayer.employee.Employee;
import com.onlineclothingstore.employees.presentationlayer.employee.EmployeeRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {
    @Mapping(target = "id", ignore = true)
    Employee requestModelToEntity(EmployeeRequestModel employeeRequestModel,
                                  EmployeeIdentifier employeeIdentifier,
                                  DepartmentIdentifier departmentIdentifier);
}
