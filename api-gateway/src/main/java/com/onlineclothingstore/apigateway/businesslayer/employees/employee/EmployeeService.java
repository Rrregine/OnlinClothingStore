package com.onlineclothingstore.apigateway.businesslayer.employees.employee;

import com.onlineclothingstore.apigateway.presentationlayer.employees.employees.EmployeeRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.employees.employees.EmployeeResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    List<EmployeeResponseModel> getAllEmployees();

    EmployeeResponseModel getEmployeeByEmployeeId(String employeeId);

    EmployeeResponseModel addEmployee(EmployeeRequestModel employeeRequestModel);

    EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId);

    void deleteEmployee(String employeeId);
}
