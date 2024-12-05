package com.onlineclothingstore.employees.presentationlayer.employee;

import com.onlineclothingstore.employees.businesslayer.employee.EmployeeService;
import com.onlineclothingstore.employees.dataaccesslayer.employee.PhoneNumber;
import com.onlineclothingstore.employees.dataaccesslayer.employee.PhoneType;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes= EmployeeController.class)
class EmployeeControllerUnitTest {
    private final String FOUND_EMPLOYEE_ID = "e5913a79-9b1e-4516-9ffd-06578e7af261";
    private final String NOT_FOUND_EMPLOYEE_ID = "e5913a79-9b1e-4516-9ffd-06578e7af110";
    private final String INVALID_EMPLOYEE_ID = "e5913a79-9b1";
    private final String BASE_URI_EMPLOYEES = "/api/v1/employees";


    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployees() {
        // Arrange
        EmployeeResponseModel employee1 = new EmployeeResponseModel();
        employee1.setEmployeeId("e5913a79-9b1e-4516-9ffd-06578e7af261");
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setEmailAddress("john.doe@example.com");
        employee1.setSalary(50000.0);
        employee1.setCommissionRate(0.05);
        employee1.setDepartmentId("1048b354-c18f-4109-8282-2a85485bfa5a");

        EmployeeResponseModel employee2 = new EmployeeResponseModel();
        employee2.setEmployeeId("e5913a79-9b1e-4516-9ffd-06578e7af262");
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setEmailAddress("jane.smith@example.com");
        employee2.setSalary(45000.0);
        employee2.setCommissionRate(0.03);
        employee2.setDepartmentId("1048b354-c18f-4109-8282-2a85485bfa5b");

        List<EmployeeResponseModel> employees = Arrays.asList(employee1, employee2);
        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Act
        ResponseEntity<List<EmployeeResponseModel>> responseEntity = employeeController.getEmployees();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(employees, responseEntity.getBody());
    }

    @Test
    public void testGetEmployeeByEmployeeIdFound() {
        // Arrange
        EmployeeResponseModel employee = new EmployeeResponseModel();
        employee.setEmployeeId("e5913a79-9b1e-4516-9ffd-06578e7af262");
        employee.setFirstName("Jane");
        employee.setLastName("Smith");
        employee.setEmailAddress("jane.smith@example.com");
        employee.setSalary(45000.0);
        employee.setCommissionRate(0.03);
        employee.setDepartmentId("1048b354-c18f-4109-8282-2a85485bfa5b");

        when(employeeService.getEmployeeByEmployeeId(FOUND_EMPLOYEE_ID)).thenReturn(employee);

        // Act
        ResponseEntity<EmployeeResponseModel> responseEntity = employeeController.getEmployeeByEmployeeId(FOUND_EMPLOYEE_ID);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(employee, responseEntity.getBody());
    }

    @Test
    public void whenNoEmployeeExists_thenReturnEmptyList(){

        // Arrange
        when(employeeService.getAllEmployees()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<EmployeeResponseModel>> responseEntity = employeeController.getEmployees();

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isEmpty());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void whenEmployeeExists_thenReturnEmployee(){
        // Arrange
        EmployeeRequestModel employeeRequestModel = buildEmployeeRequestModel();
        EmployeeResponseModel employeeResponseModel = buildEmployeeResponseModel();

        when(employeeService.addEmployee(employeeRequestModel)).thenReturn(employeeResponseModel);

        // Act
        ResponseEntity<EmployeeResponseModel> responseEntity = employeeController.addEmployee(employeeRequestModel);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(employeeResponseModel, responseEntity.getBody());
        verify(employeeService, times(1)).addEmployee(employeeRequestModel);
    }




    private EmployeeRequestModel buildEmployeeRequestModel(){

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber(PhoneType.HOME, "111-111-1111"));
        phoneNumbers.add(new PhoneNumber(PhoneType.MOBILE, "222-222-2222"));

        return EmployeeRequestModel.builder()
                .firstName("John")
                .lastName("Doe")
                .emailAddress("john@gmail.com")
                .salary(50000.0)
                .commissionRate(0.05)
                .departmentId("1048b354-c18f-4109-8282-2a85485bfa5a")
                .phoneNumbers(phoneNumbers)
                .build();
    }

    private EmployeeResponseModel buildEmployeeResponseModel(){

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber(PhoneType.HOME, "111-111-1111"));
        phoneNumbers.add(new PhoneNumber(PhoneType.MOBILE, "222-222-2222"));

        return EmployeeResponseModel.builder()
                .employeeId("e5913a79-9b1e-4516-9ffd-06578e7af261")
                .firstName("John")
                .lastName("Doe")
                .emailAddress("john@gmail.com")
                .salary(50000.0)
                .commissionRate(0.05)
                .departmentId("1048b354-c18f-4109-8282-2a85485bfa5a")
                .phoneNumbers(phoneNumbers)
                .build();
    }


}