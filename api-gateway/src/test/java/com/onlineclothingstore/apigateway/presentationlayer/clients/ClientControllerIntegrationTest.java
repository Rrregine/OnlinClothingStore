package com.onlineclothingstore.apigateway.presentationlayer.clients;

import com.onlineclothingstore.apigateway.domainclientlayer.clients.ClientsServiceClient;
import com.onlineclothingstore.apigateway.domainclientlayer.clients.PhoneNumber;
import com.onlineclothingstore.apigateway.domainclientlayer.clients.PhoneType;
import com.onlineclothingstore.apigateway.presentationlayer.employees.departments.DepartmentResponseModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ClientControllerIntegrationTest {

    private final String BASE_URI_CLIENTS = "/api/v1/clients";
    private final String FOUND_CLIENT_ID = "1048b354-c18f-4109-8282-2a85485bfa5a";
    private final String NOT_FOUND_CLIENT_ID = "c5913a79-9b1e-4516-9ffd-06578e7af111";
    private final String INVALID_CLIENT_ID = "c5913a79-9b1e";
    private final String EXISTING_CLIENT_ID = "1048b354-c18f-4109-8282-2a85485bfa5a";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ClientsServiceClient clientsServiceClient;

    /*@Test
    public void whenGetClients_thenReturnAllClients_PositivePath() {

        // Arrange
        List<ClientResponseModel> mockedClients = Arrays.asList(
                new ClientResponseModel(),
                new ClientResponseModel()
        );

        when(clientsServiceClient.getAllClients()).thenReturn(mockedClients);

        // Act and Assert
        webTestClient.get().uri("/api/v1/clients")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(DepartmentResponseModel.class)
                .value((list) -> {
                    assertNotNull(list);
                    assertEquals(mockedClients.size(), list.size());
                });
    }

    @Test
    public void whenGetClients_thenReturnEmptyList_NegativePath() {
        // Arrange - Simulate no clients existing in the system
        List<ClientResponseModel> emptyList = new ArrayList<>();

        // Act and Assert
        webTestClient.get().uri(BASE_URI_CLIENTS)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ClientResponseModel.class);

        assertTrue(emptyList.isEmpty());
    }

    @Test
    public void whenGetClientByClientId_thenReturnClient_PositivePath() {
        // Perform GET request and validate response
        webTestClient.get().uri(BASE_URI_CLIENTS + "/" + FOUND_CLIENT_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ClientResponseModel.class)
                .value(client -> {
                    assertNotNull(client);
                });
    }

    @Test
    public void whenGetClientByClientId_thenReturnNotFound_NegativePath() {
        // Perform GET request and validate response
        webTestClient.get().uri(BASE_URI_CLIENTS + "/" + NOT_FOUND_CLIENT_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown clientId: " + NOT_FOUND_CLIENT_ID);
    }

    /*
    @Test
    public void whenValidClient_thenCreateClient() {
        // Arrange
        long sizeDB = clientRepository.count();
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber(PhoneType.HOME, "111-111-1111"));
        phoneNumbers.add(new PhoneNumber(PhoneType.MOBILE, "222-222-2222"));
        ClientRequestModel clientRequestModel = new ClientRequestModel();
        clientRequestModel.setUsername("johndoe");
        clientRequestModel.setEmailAddress("johndoe@example.com");
        clientRequestModel.setStreetAddress("123 Main St");
        clientRequestModel.setCity("New York");
        clientRequestModel.setProvince("NY");
        clientRequestModel.setCountry("USA");
        clientRequestModel.setPostalCode("10001");
        clientRequestModel.setPhoneNumbers(phoneNumbers);

        // Act and Assert for Positive Path
        webTestClient.post()
                .uri(BASE_URI_CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(clientRequestModel)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ClientResponseModel.class)
                .value((clientResponseModel) -> {
                    assertNotNull(clientResponseModel);
                    assertNotNull(clientResponseModel.getClientId());
                    assertEquals(clientRequestModel.getUsername(), clientResponseModel.getUsername());
                    assertEquals(clientRequestModel.getEmailAddress(), clientResponseModel.getEmailAddress());
                    assertEquals(clientRequestModel.getStreetAddress(), clientResponseModel.getStreetAddress());
                    // Add more assertions as needed
                });
    }*/

    /*@Test
    public void whenInvalidClient_thenBadRequest_NegativePath() {
        // Arrange - Create an invalid client request model
        ClientRequestModel invalidClientRequestModel = new ClientRequestModel();
        // Set invalid data, for example, an empty username
        invalidClientRequestModel.setUsername(null); // Empty username
        invalidClientRequestModel.setEmailAddress("johndoe@example.com");
        invalidClientRequestModel.setStreetAddress("123 Main St");
        invalidClientRequestModel.setCity("New York");
        invalidClientRequestModel.setProvince("NY");
        invalidClientRequestModel.setCountry("USA");
        invalidClientRequestModel.setPostalCode("10001");
        invalidClientRequestModel.setPhoneNumbers(Collections.emptyList()); // Empty phone numbers list

        // Act and Assert for Negative Path (Bad Request)
        webTestClient.post()
                .uri(BASE_URI_CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(invalidClientRequestModel)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void whenValidClient_thenUpdateClient_PositivePath() {
        // Arrange - Create a valid client request model for updating
        ClientRequestModel validClientRequestModel = new ClientRequestModel();
        validClientRequestModel.setUsername("updatedUsername");
        validClientRequestModel.setEmailAddress("updated.email@example.com");
        validClientRequestModel.setStreetAddress("456 Oak St");
        validClientRequestModel.setCity("Los Angeles");
        validClientRequestModel.setProvince("CA");
        validClientRequestModel.setCountry("USA");
        validClientRequestModel.setPostalCode("90001");
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        validClientRequestModel.setPhoneNumbers(phoneNumbers);

        // Act and Assert for Positive Path (Updated Client)
        webTestClient.put()
                .uri(BASE_URI_CLIENTS + "/{clientId}", EXISTING_CLIENT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(validClientRequestModel)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ClientResponseModel.class)
                .value((updatedClientResponseModel) -> {
                    assertNotNull(updatedClientResponseModel);
                    assertEquals(validClientRequestModel.getUsername(), updatedClientResponseModel.getUsername());
                    assertEquals(validClientRequestModel.getEmailAddress(), updatedClientResponseModel.getEmailAddress());
                    assertEquals(validClientRequestModel.getStreetAddress(), updatedClientResponseModel.getStreetAddress());
                    // Add more assertions as needed
                });
    }

    @Test
    public void whenInvalidClientId_thenNotFound_UpdateClient_NegativePath() {
        // Arrange - Create an invalid client request model for updating
        ClientRequestModel invalidClientRequestModel = new ClientRequestModel();
        // Set valid data
        invalidClientRequestModel.setUsername("johndoe"); // Valid username
        invalidClientRequestModel.setEmailAddress("johndoe@example.com");
        invalidClientRequestModel.setStreetAddress("123 Main St");
        invalidClientRequestModel.setCity("New York");
        invalidClientRequestModel.setProvince("NY");
        invalidClientRequestModel.setCountry("USA");
        invalidClientRequestModel.setPostalCode("10001");
        invalidClientRequestModel.setPhoneNumbers(Collections.emptyList()); // Empty phone numbers list

        // Act and Assert for Negative Path (Bad Request) with invalid client ID
        webTestClient.put()
                .uri(BASE_URI_CLIENTS + "/{clientId}", "nonexistent-client-id") // Provide a non-existent client ID here
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(invalidClientRequestModel)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void whenDeleteClient_thenClientDeleted_PositivePath() {
        // Act - Delete the client using a DELETE request
        webTestClient.delete()
                .uri(BASE_URI_CLIENTS + "/{clientId}", EXISTING_CLIENT_ID)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void whenDeleteClient_withInvalidId_thenNotFound_NegativePath() {
        // Act - Attempt to delete the client using a DELETE request with an invalid ID
        webTestClient.delete()
                .uri(BASE_URI_CLIENTS + "/{clientId}", INVALID_CLIENT_ID)
                .exchange()
                .expectStatus().isNotFound();
    } */

}