package com.onlineclothingstore.employees.dataaccesslayer.employee;

import com.onlineclothingstore.employees.dataaccesslayer.department.DepartmentIdentifier;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByEmployeeId_ExistingId_ShouldReturnEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setEmployeeIdentifier(new EmployeeIdentifier("123"));
        employeeRepository.save(employee);

        // Act
        Employee foundEmployee = employeeRepository.findByEmployeeIdentifier_EmployeeId("123");

        // Assert
        assertEquals("123", foundEmployee.getEmployeeIdentifier().getEmployeeId());
    }

    @Test
    public void testFindByEmployeeId_NonExistingId_ShouldReturnNull() {
        // Arrange

        // Act
        Employee foundEmployee = employeeRepository.findByEmployeeIdentifier_EmployeeId("456");

        // Assert
        assertNull(foundEmployee);
    }

    @Test
    public void testFindAllByDepartmentId_ExistingId_ShouldReturnEmployees() {
        // Arrange
        Employee employee1 = new Employee();
        employee1.setDepartmentIdentifier(new DepartmentIdentifier("dept1"));
        employeeRepository.save(employee1);

        Employee employee2 = new Employee();
        employee2.setDepartmentIdentifier(new DepartmentIdentifier("dept2"));
        employeeRepository.save(employee2);

        // Act
        List<Employee> employees = employeeRepository.findAllByDepartmentIdentifier_DepartmentId("dept1");

        // Assert
        assertEquals(1, employees.size());
        assertEquals("dept1", employees.get(0).getDepartmentIdentifier().getDepartmentId());
    }

    @Test
    public void testFindAllByDepartmentId_NonExistingId_ShouldReturnEmptyList() {
        // Arrange

        // Act
        List<Employee> employees = employeeRepository.findAllByDepartmentIdentifier_DepartmentId("dept3");

        // Assert
        assertEquals(0, employees.size());
    }
}