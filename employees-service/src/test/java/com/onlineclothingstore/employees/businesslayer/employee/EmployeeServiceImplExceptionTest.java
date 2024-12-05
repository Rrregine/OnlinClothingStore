package com.onlineclothingstore.employees.businesslayer.employee;

import com.onlineclothingstore.employees.dataaccesslayer.employee.EmployeeRepository;
import com.onlineclothingstore.employees.mapperlayer.employee.EmployeeRequestMapper;
import com.onlineclothingstore.employees.mapperlayer.employee.EmployeeResponseMapper;
import com.onlineclothingstore.employees.presentationlayer.employee.EmployeeRequestModel;
import com.onlineclothingstore.employees.utils.exceptions.IsEmptyException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceImplExceptionTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeRequestMapper employeeRequestMapper;

    @MockBean
    private EmployeeResponseMapper employeeResponseMapper;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Test
    public void testAddEmployeeWithEmptyFirstName() {
        // Arrange
        EmployeeRequestModel requestModel = new EmployeeRequestModel();
        requestModel.setLastName("Doe");

        // Act and Assert
        IsEmptyException exception = assertThrows(IsEmptyException.class, () -> {
            employeeService.addEmployee(requestModel);
        });

        // Assert that the correct exception message is thrown
        assertEquals("First name cannot be empty! ", exception.getMessage());
    }

    @Test
    public void testAddEmployeeWithEmptyLastName() {
        // Arrange
        EmployeeRequestModel requestModel = new EmployeeRequestModel();
        requestModel.setFirstName("John");
        // Note: Last name is intentionally left empty

        // Act and Assert
        IsEmptyException exception = assertThrows(IsEmptyException.class, () -> {
            employeeService.addEmployee(requestModel);
        });

        // Assert that the correct exception message is thrown
        assertEquals("Last name cannot be empty! ", exception.getMessage());
    }

    @Test
    public void testAddEmployeeWithEmptyFirstName_NegativePath() {
        // Arrange
        EmployeeRequestModel requestModel = new EmployeeRequestModel();
        requestModel.setLastName("Doe");

        // Act and Assert
        try {
            employeeService.addEmployee(requestModel);
            fail("Expected IsEmptyException was not thrown");
        } catch (IsEmptyException exception) {
            // Assert that the correct exception message is thrown
            assertEquals("First name cannot be empty! ", exception.getMessage());
        }
    }

    @Test
    public void testAddEmployeeWithEmptyLastName_NegativePath() {
        // Arrange
        EmployeeRequestModel requestModel = new EmployeeRequestModel();
        requestModel.setFirstName("John");
        // Note: Last name is intentionally left empty

        // Act and Assert
        try {
            employeeService.addEmployee(requestModel);
            fail("Expected IsEmptyException was not thrown");
        } catch (IsEmptyException exception) {
            // Assert that the correct exception message is thrown
            assertEquals("Last name cannot be empty! ", exception.getMessage());
        }
    }

    @Test
    public void testIsEmptyExceptionWithoutMessage() {
        // Arrange

        // Act
        IsEmptyException exception = new IsEmptyException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    public void testIsEmptyExceptionWithMessage() {
        // Arrange
        String errorMessage = "This is an error message.";

        // Act
        IsEmptyException exception = new IsEmptyException(errorMessage);

        // Assert
        assertEquals(errorMessage, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testIsEmptyExceptionWithMessageAndCause() {
        // Arrange
        String errorMessage = "This is an error message.";
        Throwable cause = new IllegalArgumentException("Invalid argument.");

        // Act
        IsEmptyException exception = new IsEmptyException(errorMessage, cause);

        // Assert
        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

}