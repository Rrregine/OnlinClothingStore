package com.onlineclothingstore.employees.mapperlayer.employee;

import com.onlineclothingstore.employees.dataaccesslayer.employee.Employee;
import com.onlineclothingstore.employees.presentationlayer.employee.EmployeeResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeResponseMapper {

    @Mapping(expression = "java(employee.getEmployeeIdentifier().getEmployeeId())", target = "employeeId")
    @Mapping(expression = "java(employee.getDepartmentIdentifier().getDepartmentId())", target = "departmentId")
    EmployeeResponseModel entityToResponseModel(Employee employee);

    List<EmployeeResponseModel> entityListToResponseModelList(List<Employee> employeeList);
}
