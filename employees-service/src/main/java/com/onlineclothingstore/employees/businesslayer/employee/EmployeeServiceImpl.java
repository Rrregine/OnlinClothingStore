package com.onlineclothingstore.employees.businesslayer.employee;

import com.onlineclothingstore.employees.dataaccesslayer.employee.EmployeeIdentifier;
import com.onlineclothingstore.employees.dataaccesslayer.department.DepartmentIdentifier;
import com.onlineclothingstore.employees.dataaccesslayer.employee.Employee;
import com.onlineclothingstore.employees.dataaccesslayer.employee.EmployeeRepository;
import com.onlineclothingstore.employees.mapperlayer.employee.EmployeeRequestMapper;
import com.onlineclothingstore.employees.mapperlayer.employee.EmployeeResponseMapper;
import com.onlineclothingstore.employees.presentationlayer.employee.EmployeeRequestModel;
import com.onlineclothingstore.employees.presentationlayer.employee.EmployeeResponseModel;
import com.onlineclothingstore.employees.utils.exceptions.IsEmptyException;
import com.onlineclothingstore.employees.utils.exceptions.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeResponseMapper employeeResponseMapper;
    private final EmployeeRequestMapper employeeRequestMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeResponseMapper employeeResponseMapper, EmployeeRequestMapper employeeRequestMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeResponseMapper = employeeResponseMapper;
        this.employeeRequestMapper = employeeRequestMapper;
    }

    @Override
    public List<EmployeeResponseModel> getAllEmployees() {

        List<Employee> employeeEntities = employeeRepository.findAll();

        return employeeResponseMapper.entityListToResponseModelList(employeeEntities);
    }

    @Override
    public EmployeeResponseModel getEmployeeByEmployeeId(String employeeId) {

        Employee foundEmployee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);

        if( foundEmployee == null){
            throw new NotFoundException("Unknown employeeId: " + employeeId);
        }

        return employeeResponseMapper.entityToResponseModel(foundEmployee);
    }

    @Override
    public EmployeeResponseModel addEmployee(EmployeeRequestModel employeeRequestModel) {

        if (employeeRequestModel.getFirstName().isEmpty()) {
            throw new IsEmptyException("First name cannot be empty! ");
        }

        if (employeeRequestModel.getLastName().isEmpty()) {
            throw new IsEmptyException("Last name cannot be empty! ");
        }

        Employee employee = employeeRequestMapper.requestModelToEntity(employeeRequestModel, new EmployeeIdentifier(), new DepartmentIdentifier(employeeRequestModel.getDepartmentId()));

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeResponseMapper.entityToResponseModel(savedEmployee);
    }

    @Override
    public EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId) {
        Employee foundEmployee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);

        if (employeeRequestModel.getFirstName() == null) {
            throw new IsEmptyException("First name cannot be empty! ");
        }

        if (employeeRequestModel.getLastName() == null) {
            throw new IsEmptyException("Last name cannot be empty! ");
        }

        if (foundEmployee == null){
            throw new NotFoundException("Unknown employeeId : " + employeeId);
        }

        Employee employee = employeeRequestMapper.requestModelToEntity(employeeRequestModel, foundEmployee.getEmployeeIdentifier(), new DepartmentIdentifier(employeeRequestModel.getDepartmentId()));
        employee.setId(foundEmployee.getId());
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeResponseMapper.entityToResponseModel(savedEmployee);
    }

    @Override
    public void deleteEmployee(String employeeId) {

        Employee foundEmployee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);

        if (foundEmployee == null){
            throw new NotFoundException("Unknown employeeId : " + employeeId);
        }

        employeeRepository.delete(foundEmployee);
    }

}
