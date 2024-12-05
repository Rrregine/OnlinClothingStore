package com.onlineclothingstore.employees.presentationlayer.department;

import com.onlineclothingstore.employees.dataaccesslayer.department.DepartmentRepository;
import com.onlineclothingstore.employees.dataaccesslayer.department.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DepartmentControllerIntegrationTest {
    private final String BASE_URI_DEPARTMENTS = "/api/v1/departments";
    private final String FOUND_DEPARTMENT_ID = "a5913a79-9b1e-4516-9ffd-06578e7af261";
    private final String NOT_FOUND_EMPLOYEE_ID = "a5913a79-9b1e-4516-9ffd-06578e7af000";
    private final String INVALID_DEPARTMENT_ID = "e5913a79-9b1e";
    private final String EXISTING_DEPARTMENT_ID = "a5913a79-9b1e-4516-9ffd-06578e7af261";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void whenGetDepartments_thenReturnAllDepartments_PositivePath() {
        // Arrange
        Long sizeDB = departmentRepository.count();

        // Act and Assert
        webTestClient.get().uri(BASE_URI_DEPARTMENTS)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(DepartmentResponseModel.class)
                .value((list) -> {
                    assertNotNull(list);
                    assertTrue(list.size() == sizeDB);
                });
    }

    @Test
    public void whenGetDepartmentByDepartmentId_thenReturnDepartment_PositivePath() {
        // Perform GET request and validate response
        webTestClient.get().uri(BASE_URI_DEPARTMENTS + "/" + FOUND_DEPARTMENT_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(DepartmentResponseModel.class)
                .value(department -> {
                    assertNotNull(department);
                });
    }

    @Test
    public void whenGetDepartmentByDepartmentId_thenReturnNotFound_NegativePath() {
        // Perform GET request with non-existent department ID and validate response
        webTestClient.get().uri(BASE_URI_DEPARTMENTS + "/" + NOT_FOUND_EMPLOYEE_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown departmentId: " + NOT_FOUND_EMPLOYEE_ID);
    }

    @Test
    public void whenValidDepartment_thenCreateDepartment() {
        // Arrange
        DepartmentRequestModel departmentRequestModel = new DepartmentRequestModel();
        departmentRequestModel.setName("HR Department");
        departmentRequestModel.setHeadCount(10);

        // Act and Assert for Positive Path
        webTestClient.post()
                .uri(BASE_URI_DEPARTMENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(departmentRequestModel)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(DepartmentResponseModel.class)
                .value((departmentResponseModel) -> {
                    assertNotNull(departmentResponseModel);
                    assertEquals(departmentRequestModel.getName(), departmentResponseModel.getName());
                    assertEquals(departmentRequestModel.getHeadCount(), departmentResponseModel.getHeadCount());
                });
    }

    @Test
    public void whenInvalidDepartment_thenBadRequest_NegativePath() {
        // Arrange - Create an invalid department request model
        DepartmentRequestModel invalidDepartmentRequestModel = new DepartmentRequestModel();
        // Set invalid data, for example, an empty department name
        invalidDepartmentRequestModel.setName(null); // Empty department name
        invalidDepartmentRequestModel.setHeadCount(10); // Valid headcount

        // Act and Assert for Negative Path (Bad Request)
        webTestClient.post()
                .uri(BASE_URI_DEPARTMENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(invalidDepartmentRequestModel)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void whenValidDepartment_thenUpdateDepartment_PositivePath() {
        // Arrange - Create a valid department request model for updating
        DepartmentRequestModel validDepartmentRequestModel = new DepartmentRequestModel();
        validDepartmentRequestModel.setName("Updated Department Name");
        validDepartmentRequestModel.setHeadCount(10); // Updated headcount

        // Act and Assert for Positive Path (Updated Department)
        webTestClient.put()
                .uri(BASE_URI_DEPARTMENTS + "/{departmentId}", EXISTING_DEPARTMENT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(validDepartmentRequestModel)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(DepartmentResponseModel.class)
                .value((updatedDepartmentResponseModel) -> {
                    assertNotNull(updatedDepartmentResponseModel);
                    assertEquals(validDepartmentRequestModel.getName(), updatedDepartmentResponseModel.getName());
                    assertEquals(validDepartmentRequestModel.getHeadCount(), updatedDepartmentResponseModel.getHeadCount());
                });
    }

    @Test
    public void whenInvalidDepartmentId_thenNotFound_UpdateDepartment_NegativePath() {
        // Arrange - Create an invalid department request model for updating
        DepartmentRequestModel invalidDepartmentRequestModel = new DepartmentRequestModel();
        // Set valid data
        invalidDepartmentRequestModel.setName("Invalid Department Name"); // Invalid name
        invalidDepartmentRequestModel.setHeadCount(-1); // Invalid headcount

        // Act and Assert for Negative Path (Bad Request) with invalid department ID
        webTestClient.put()
                .uri(BASE_URI_DEPARTMENTS + "/{departmentId}", "nonexistent-department-id") // Provide a non-existent department ID here
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(invalidDepartmentRequestModel)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void whenDeleteDepartment_thenDepartmentDeleted_PositivePath() {

        // Act - Delete the department using a DELETE request
        webTestClient.delete()
                .uri(BASE_URI_DEPARTMENTS + "/{departmentId}", EXISTING_DEPARTMENT_ID)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void whenDeleteDepartment_withInvalidId_thenNotFound_NegativePath() {

        // Act - Attempt to delete the department using a DELETE request with an invalid ID
        webTestClient.delete()
                .uri(BASE_URI_DEPARTMENTS + "/{departmentId}", INVALID_DEPARTMENT_ID)
                .exchange()
                .expectStatus().isNotFound();
    }

}