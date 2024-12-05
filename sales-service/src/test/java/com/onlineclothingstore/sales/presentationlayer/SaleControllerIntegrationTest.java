package com.onlineclothingstore.sales.presentationlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SaleControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ObjectMapper objectMapper;

  /*  @Test
    public void testGetClientOrderBySaleId() {
        String clientId = "9a7f32d6-4286-4c63-af92-d57542607c3e";
        String saleId = "product10";

        webTestClient.get()
                .uri("/api/v1/clients/{clientId}/orders/{saleId}", clientId, saleId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.clientId").isEqualTo(clientId)
                .jsonPath("$.saleId").isEqualTo(saleId);
    }

    @Test
    public void testAddClientOrder() throws Exception {
        String clientId = "9a7f32d6-4286-4c63-af92-d57542607c3e";
        SaleRequestModel requestModel = new SaleRequestModel(); // Create a SaleRequestModel object

        webTestClient.post()
                .uri("/api/v1/clients/{clientId}/orders", clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(requestModel))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.clientId").isEqualTo(clientId);
    }

    @Test
    public void testGetAllClientOrdersByClient() {
        String clientId = "10d84b69-7d92-4e3b-89e1-f5b124e24935";

        webTestClient.get()
                .uri("/api/v1/clients/{clientId}/orders", clientId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray();
    }

    @Test
    public void testUpdateClientOrder() throws Exception {
        String clientId = "10d84b69-7d92-4e3b-89e1-f5b124e24935";
        String saleId = "001";
        SaleRequestModel requestModel = new SaleRequestModel(); // Create a SaleRequestModel object

        webTestClient.put()
                .uri("/api/v1/clients/{clientId}/orders/{saleId}", clientId, saleId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(requestModel))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.clientId").isEqualTo(clientId)
                .jsonPath("$.saleId").isEqualTo(saleId);
    }

    @Test
    public void testDeleteClientOrderFromClient() {
        String clientId = "10d84b69-7d92-4e3b-89e1-f5b124e24935";
        String saleId = "001";

        webTestClient.delete()
                .uri("/api/v1/clients/{clientId}/orders/{saleId}", clientId, saleId)
                .exchange()
                .expectStatus().isNoContent();
    } */
}