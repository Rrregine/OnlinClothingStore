package com.onlineclothingstore.employees.businesslayer.employee;

import com.onlineclothingstore.employees.presentationlayer.employee.EmployeeRequestModel;
import com.onlineclothingstore.employees.presentationlayer.employee.EmployeeResponseModel;
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
