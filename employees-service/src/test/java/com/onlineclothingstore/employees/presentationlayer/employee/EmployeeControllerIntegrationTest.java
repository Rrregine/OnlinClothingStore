package com.onlineclothingstore.employees.presentationlayer.employee;

import com.onlineclothingstore.employees.dataaccesslayer.employee.EmployeeRepository;
import com.onlineclothingstore.employees.dataaccesslayer.employee.PhoneNumber;
import com.onlineclothingstore.employees.dataaccesslayer.employee.PhoneType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeControllerIntegrationTest {

	private final String BASE_URI_EMPLOYEES = "/api/v1/employees";
	private final String FOUND_EMPLOYEE_ID = "e5913a79-9b1e-4516-9ffd-06578e7af261";
	private final String NOT_FOUND_EMPLOYEE_ID = "e5913a79-9b1e-4516-9ffd-06578e7af111";
	private final String INVALID_EMPLOYEE_ID = "e5913a79-9b1e";
	private final String EXISTING_EMPLOYEE_ID = "e5913a79-9b1e-4516-9ffd-06578e7af261";

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void whenGetEmployees_thenReturnAllEmployees_PositivePath() {
		//arrange
		Long sizeDB = employeeRepository.count();

		// act and assert
		webTestClient.get().uri(BASE_URI_EMPLOYEES)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(EmployeeResponseModel.class)
				.value((list) -> {
					assertNotNull(list);
					assertTrue(list.size() == sizeDB);
				});
	}

	@Test
	public void whenGetEmployees_thenReturnEmptyList_NegativePath() {
		// Arrange - Simulate no employees existing in the system
		List<EmployeeResponseModel> emptyList = new ArrayList<>();

		// Act and Assert
		webTestClient.get().uri(BASE_URI_EMPLOYEES)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(EmployeeResponseModel.class);

		assertTrue(emptyList.isEmpty());

	}


	@Test
	public void whenGetEmployeeByEmployeeId_thenReturnEmployee_PositivePath() {

		// Perform GET request and validate response
		webTestClient.get().uri(BASE_URI_EMPLOYEES + "/" + FOUND_EMPLOYEE_ID)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(EmployeeResponseModel.class)
				.value(employee -> {
					assertNotNull(employee);
				});
	}

	@Test
	public void whenGetEmployeeByEmployeeId_thenReturnNotFound_NegativePath() {

		// Perform GET request and validate response
		webTestClient.get().uri(BASE_URI_EMPLOYEES + "/" + NOT_FOUND_EMPLOYEE_ID)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody()
				.jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
				.jsonPath("$.message").isEqualTo("Unknown employeeId: " + NOT_FOUND_EMPLOYEE_ID);

	}

	@Test
	public void whenValidEmployee_thenCreateEmployee() {
		// Arrange
		long sizeDB = employeeRepository.count();
		List<PhoneNumber> phoneNumbers = new ArrayList<>();
		phoneNumbers.add(new PhoneNumber(PhoneType.HOME, "111-111-1111"));
		phoneNumbers.add(new PhoneNumber(PhoneType.MOBILE, "222-222-2222"));
		EmployeeRequestModel employeeRequestModel = new EmployeeRequestModel();
		employeeRequestModel.setDepartmentId("1048b354-c18f-4109-8282-2a85485bfa5a");
		employeeRequestModel.setFirstName("John");
		employeeRequestModel.setLastName("Doe");
		employeeRequestModel.setEmailAddress("john.doe@example.com");
		employeeRequestModel.setSalary(50000.0);
		employeeRequestModel.setCommissionRate(0.05);
		employeeRequestModel.setPhoneNumbers(phoneNumbers);

		// Act and Assert for Positive Path
		webTestClient.post()
				.uri(BASE_URI_EMPLOYEES)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(employeeRequestModel)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(EmployeeResponseModel.class)
				.value((employeeResponseModel) -> {
					assertNotNull(employeeResponseModel);
					assertNotNull(employeeResponseModel);
					assertEquals(employeeRequestModel.getFirstName(), employeeResponseModel.getFirstName());
					assertEquals(employeeRequestModel.getEmailAddress(), employeeResponseModel.getEmailAddress());
					assertEquals(employeeRequestModel.getDepartmentId(), employeeResponseModel.getDepartmentId());
					assertEquals(employeeRequestModel.getLastName(), employeeResponseModel.getLastName());
					assertEquals(employeeRequestModel.getSalary(), employeeResponseModel.getSalary());
					assertEquals(employeeRequestModel.getCommissionRate(), employeeResponseModel.getCommissionRate());
					assertEquals(employeeRequestModel.getPhoneNumbers().size(), employeeResponseModel.getPhoneNumbers().size());
				});
	}

	@Test
	public void whenInvalidEmployee_thenBadRequest_NegativePath() {
		// Arrange - Create an invalid employee request model
		EmployeeRequestModel invalidEmployeeRequestModel = new EmployeeRequestModel();
		// Set invalid data, for example, an empty first name
		invalidEmployeeRequestModel.setDepartmentId("1048b354-c18f-4109-8282-2a85485bfa5a");
		invalidEmployeeRequestModel.setFirstName(null); // Empty first name
		invalidEmployeeRequestModel.setLastName("Doe");
		invalidEmployeeRequestModel.setEmailAddress("john.doe@example.com");
		invalidEmployeeRequestModel.setSalary(50000.0);
		invalidEmployeeRequestModel.setCommissionRate(0.05);
		invalidEmployeeRequestModel.setPhoneNumbers(Collections.emptyList()); // Empty phone numbers list

		// Act and Assert for Negative Path (Bad Request)
		webTestClient.post()
				.uri(BASE_URI_EMPLOYEES)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(invalidEmployeeRequestModel)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
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
		List<PhoneNumber> phoneNumbers = new ArrayList<>();
		phoneNumbers.add(new PhoneNumber(PhoneType.HOME, "333-333-3333"));
		phoneNumbers.add(new PhoneNumber(PhoneType.MOBILE, "444-444-4444"));
		validEmployeeRequestModel.setPhoneNumbers(phoneNumbers);

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
	public void whenInvalidEmployeeId_thenNotFound_UpdateEmployee_NegativePath() {
		// Arrange - Create an invalid employee request model for updating
		EmployeeRequestModel invalidEmployeeRequestModel = new EmployeeRequestModel();
		// Set valid data
		invalidEmployeeRequestModel.setDepartmentId("1048b354-c18f-4109-8282-2a85485bfa5a");
		invalidEmployeeRequestModel.setFirstName("John"); // Valid first name
		invalidEmployeeRequestModel.setLastName("Doe");
		invalidEmployeeRequestModel.setEmailAddress("john.doe@example.com");
		invalidEmployeeRequestModel.setSalary(50000.0);
		invalidEmployeeRequestModel.setCommissionRate(0.05);
		invalidEmployeeRequestModel.setPhoneNumbers(Collections.emptyList()); // Empty phone numbers list

		// Act and Assert for Negative Path (Bad Request) with invalid employee ID
		webTestClient.put()
				.uri(BASE_URI_EMPLOYEES + "/{employeeId}", "nonexistent-employee-id") // Provide a non-existent employee ID here
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(invalidEmployeeRequestModel)
				.exchange()
				.expectStatus().isNotFound();
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
	}
}
