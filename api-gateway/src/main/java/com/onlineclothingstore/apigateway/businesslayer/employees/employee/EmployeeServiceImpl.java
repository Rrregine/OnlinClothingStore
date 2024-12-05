package com.onlineclothingstore.apigateway.businesslayer.employees.employee;

import com.onlineclothingstore.apigateway.domainclientlayer.employees.employees.EmployeesServiceClient;
import com.onlineclothingstore.apigateway.mappinglayer.employees.EmployeeResponseMapper;
import com.onlineclothingstore.apigateway.presentationlayer.employees.employees.EmployeeRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.employees.employees.EmployeeResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeResponseMapper employeeResponseMapper;
    private final EmployeesServiceClient employeesServiceClient;

    public EmployeeServiceImpl(EmployeeResponseMapper employeeResponseMapper,
                               EmployeesServiceClient employeesServiceClient) {
        this.employeeResponseMapper = employeeResponseMapper;
        this.employeesServiceClient = employeesServiceClient;
    }


    @Override
    public List<EmployeeResponseModel> getAllEmployees() {
        return employeeResponseMapper.responseModelListToResponseModelList(employeesServiceClient.getAllEmployees());
    }

    @Override
    public EmployeeResponseModel getEmployeeByEmployeeId(String employeeId) {
        return employeeResponseMapper.responseModelToResponseModel(employeesServiceClient.getEmployeeByEmployeeId(employeeId));
    }

    @Override
    public EmployeeResponseModel addEmployee(EmployeeRequestModel employeeRequestModel) {
        return employeeResponseMapper.responseModelToResponseModel(employeesServiceClient.addEmployee(employeeRequestModel));
    }

    @Override
    public EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId) {
        return employeeResponseMapper.responseModelToResponseModel(employeesServiceClient.updateEmployeeByEmployeeId(employeeRequestModel, employeeId));
    }

    @Override
    public void deleteEmployee(String employeeId) {
        employeesServiceClient.deleteEmployeeByEmployeeId(employeeId);
    }
}
