package com.onlineclothingstore.apigateway.presentationlayer.employees.employees;

import com.onlineclothingstore.apigateway.businesslayer.employees.employee.EmployeeService;
import com.onlineclothingstore.apigateway.domainclientlayer.employees.employees.PhoneNumber;
import com.onlineclothingstore.apigateway.domainclientlayer.employees.employees.PhoneType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EmployeeControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EmployeeService employeeService;

    private final String BASE_URI_EMPLOYEES = "/api/v1/employees";
    private final String FOUND_EMPLOYEE_ID = "e5913a79-9b1e-4516-9ffd-06578e7af261";
    private final String NOT_FOUND_EMPLOYEE_ID = "e5913a79-9b1e-4516-9ffd-06578e7af111";
    private final String INVALID_EMPLOYEE_ID = "e5913a79-9b1e";
    private final String EXISTING_EMPLOYEE_ID = "e5913a79-9b1e-4516-9ffd-06578e7af261";

    @BeforeEach
    public void setUp() {
        // Setup test data or mock behavior as needed
    }

 /*   @Test
    public void testGetAllEmployees() {
        webTestClient.get().uri("/api/v1/employees")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(EmployeeResponseModel.class);
        // You can add additional assertions here based on your service behavior
    }

    @Test
    public void testGetEmployeeByEmployeeId() {
        String employeeId = "123"; // Assuming a valid employee ID
        webTestClient.get().uri("/api/v1/employees/{employeeId}", employeeId)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(EmployeeResponseModel.class);
        // You can add additional assertions here based on your service behavior
    }

    @Test
    public void testAddEmployee() {
        EmployeeRequestModel requestModel = new EmployeeRequestModel();
        // Set properties for the request model
        webTestClient.post().uri("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestModel)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(EmployeeResponseModel.class);
        // You can add additional assertions here based on your service behavior
    }

    @Test
    public void whenValidEmployee_thenUpdateEmployee_PositivePath() {
        // Arrange - Create a valid employee request model for updating
        EmployeeRequestModel validEmployeeRequestModel = new EmployeeRequestModel();
        validEmployeeRequestModel.setDepartmentId("1048b354-c18f-4109-8282-2a85485bfa5a");
        validEmployeeRequestModel.setFirstName("Updated First Name");
        validEmployeeRequestModel.setLastName("Updated Last Name");
        validEmployeeRequestModel.setEmailAddress("updated.email@example.com");
        validEmployeeRequestModel.setSalary(60000.0);
        validEmployeeRequestModel.setCommissionRate(0.06);


        // Act and Assert for Positive Path (Updated Employee)
        webTestClient.put()
                .uri(BASE_URI_EMPLOYEES + "/{employeeId}", EXISTING_EMPLOYEE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(validEmployeeRequestModel)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(EmployeeResponseModel.class)
                .value((updatedEmployeeResponseModel) -> {
                    assertNotNull(updatedEmployeeResponseModel);
                    assertEquals(validEmployeeRequestModel.getFirstName(), updatedEmployeeResponseModel.getFirstName());
                    assertEquals(validEmployeeRequestModel.getEmailAddress(), updatedEmployeeResponseModel.getEmailAddress());
                    assertEquals(validEmployeeRequestModel.getDepartmentId(), updatedEmployeeResponseModel.getDepartmentId());
                    assertEquals(validEmployeeRequestModel.getLastName(), updatedEmployeeResponseModel.getLastName());
                    assertEquals(validEmployeeRequestModel.getSalary(), updatedEmployeeResponseModel.getSalary());
                    assertEquals(validEmployeeRequestModel.getCommissionRate(), updatedEmployeeResponseModel.getCommissionRate());
                    assertEquals(validEmployeeRequestModel.getPhoneNumbers().size(), updatedEmployeeResponseModel.getPhoneNumbers().size());
                    // Add more assertions as needed
                });
    }

    @Test
    public void whenDeleteEmployee_thenEmployeeDeleted_PositivePath() {
        // Act - Delete the employee using a DELETE request
        webTestClient.delete()
                .uri(BASE_URI_EMPLOYEES + "/{employeeId}", EXISTING_EMPLOYEE_ID)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void whenDeleteEmployee_withInvalidId_thenNotFound_NegativePath() {
        // Act - Attempt to delete the employee using a DELETE request with an invalid ID
        webTestClient.delete()
                .uri(BASE_URI_EMPLOYEES + "/{employeeId}", INVALID_EMPLOYEE_ID)
                .exchange()
                .expectStatus().isNotFound();
    } */
}