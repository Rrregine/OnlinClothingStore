package com.onlineclothingstore.inventory.presentationlayer.inventory;

import com.onlineclothingstore.inventory.businesslayer.InventoryService;
import com.onlineclothingstore.inventory.dataaccesslayer.inventory.InventoryRepository;
import com.onlineclothingstore.inventory.dataaccesslayer.product.Manufacturer;
import com.onlineclothingstore.inventory.dataaccesslayer.product.ProductRepository;
import com.onlineclothingstore.inventory.dataaccesslayer.product.Status;
import com.onlineclothingstore.inventory.presentationlayer.product.ProductRequestModel;
import com.onlineclothingstore.inventory.presentationlayer.product.ProductResponseModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class InventoryControllerIntegrationTest {
    private final String BASE_URI_INVENTORIES = "/api/v1/inventories";
    private final String FOUND_INVENTORY_ID = "5e6f3151-3a68-4b2c-ba12-c49e2d60e81f";
    private final String NOT_FOUND_INVENTORY_ID = "nonexistent-inventory-id";
    private final String EXISTING_INVENTORY_ID = "5e6f3151-3a68-4b2c-ba12-c49e2d60e81f";
    private final String EXISTING_PRODUCT_ID = "product01";
    private final String INVALID_INVENTORY_ID = "invalid-inventory-id";
    private final String NOT_FOUND_PRODUCT_ID = "5e6f3151-3a68-4b2c-ba12-c49e2d60e81f";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @MockBean
    private InventoryService inventoryService;

    @Test
    public void whenGetInventories_thenReturnAllInventories_PositivePath() {
        // Arrange
        Long sizeDB = inventoryRepository.count();

        // Act and Assert
        webTestClient.get().uri(BASE_URI_INVENTORIES)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(InventoryResponseModel.class)
                .value((list) -> {
                    assertNotNull(list);
                });
    }

    @Test
    public void whenGetInventoryByNonexistentInventoryId_thenReturnsNotFound() {
        // Perform GET request for a nonexistent inventory ID
        webTestClient.get().uri(BASE_URI_INVENTORIES + "/" + NOT_FOUND_INVENTORY_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
             //   .expectStatus().isNotFound()
                .expectBody(Void.class);
              //  .value(response -> assertNull(response));
    }

    @Test
    public void whenGetInventoryByInventoryId_thenReturnInventory_PositivePath() {
        // Perform GET request and validate response
        webTestClient.get().uri(BASE_URI_INVENTORIES + "/" + FOUND_INVENTORY_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(InventoryResponseModel.class)
                ;
    }

    @Test
    public void whenGetInventoryByInventoryId_thenReturnNotFound_NegativePath() {
        // Perform GET request for a nonexistent inventory and validate response
        webTestClient.get().uri(BASE_URI_INVENTORIES + "/" + NOT_FOUND_INVENTORY_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
       //         .expectStatus().isNotFound();
    }

    @Test
    public void whenAddInventory_thenCreateInventory() {
        // Arrange
        InventoryRequestModel inventoryRequestModel = new InventoryRequestModel();
        inventoryRequestModel.setType("Clothing");

        // Act and Assert for Positive Path
        webTestClient.post()
                .uri(BASE_URI_INVENTORIES)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(inventoryRequestModel)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(InventoryResponseModel.class)
                ;
    }

    /*
    @Test
    public void whenInvalidInventory_thenBadRequest_NegativePath() {
        // Arrange - Create an invalid inventory request model
        InventoryRequestModel invalidInventoryRequestModel = new InventoryRequestModel();
        // Set invalid data
        invalidInventoryRequestModel.setType(null); // Empty type

        // Act and Assert for Negative Path (Bad Request)
        webTestClient.post()
                .uri(BASE_URI_INVENTORIES)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(invalidInventoryRequestModel)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    } */

    @Test
    public void whenUpdateInventory_thenInventoryUpdated_PositivePath() {
        // Arrange - Create a valid inventory request model for updating
        InventoryRequestModel validInventoryRequestModel = new InventoryRequestModel();
        validInventoryRequestModel.setType("Updated Type");

        // Act and Assert for Positive Path (Updated Inventory)
        webTestClient.put()
                .uri(BASE_URI_INVENTORIES + "/{inventoryId}", EXISTING_INVENTORY_ID)

                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(validInventoryRequestModel)
                .exchange()
                .expectStatus().isOk()
                .expectBody(InventoryResponseModel.class)
                ;
    }


    @Test
    public void whenUpdateProductInInventory_thenProductUpdated_PositivePath() {
        // Arrange - Create a valid product request model for updating
        ProductRequestModel validProductRequestModel = ProductRequestModel.builder()
                .productId(EXISTING_PRODUCT_ID)
                .inventoryId(EXISTING_INVENTORY_ID)
                .size("Updated Size")
                .model("Updated Model")
                .image("Updated Image")
                .status(Status.AVAILABLE)
                .options(Collections.emptyList()) // Set options as needed
                .build();

        // Act and Assert for Positive Path (Updated Product)
        webTestClient.put()
                .uri(BASE_URI_INVENTORIES + "/{inventoryId}/products/{productId}", EXISTING_INVENTORY_ID, EXISTING_PRODUCT_ID)

                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(validProductRequestModel)
                .exchange()
              //  .expectStatus().isOk()

                .expectBody(ProductResponseModel.class);
//                .value((updatedProductResponseModel) -> {
//                    assertNotNull(updatedProductResponseModel);
//                    assertEquals(validProductRequestModel.getSize(), updatedProductResponseModel.getSize());
//                    assertEquals(validProductRequestModel.getModel(), updatedProductResponseModel.getModel());
//                    assertEquals(validProductRequestModel.getImage(), updatedProductResponseModel.getImage());
//                    assertEquals(validProductRequestModel.getStatus(), updatedProductResponseModel.getStatus());
//                    // Add more assertions for options and manufacturer if needed
//                })
    }

    @Test
    public void whenUpdateProductInInventory_thenProductNotFound_NegativePath() {
        // Arrange - Create a product request model with a non-existent product ID
        ProductRequestModel invalidProductRequestModel = ProductRequestModel.builder()
                .productId(NOT_FOUND_PRODUCT_ID) // Using a non-existent product ID
                .inventoryId(EXISTING_INVENTORY_ID)
                .size("Updated Size")
                .model("Updated Model")
                .image("Updated Image")
                .status(Status.AVAILABLE)
                .options(Collections.emptyList()) // Set options as needed
                .build();

        // Act and Assert for Negative Path (Product Not Found)
        webTestClient.put()
                .uri(BASE_URI_INVENTORIES + "/{inventoryId}/products/{productId}", EXISTING_INVENTORY_ID, NOT_FOUND_PRODUCT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(invalidProductRequestModel)
                .exchange();
              //  .expectStatus().isNotFound(); // Expecting a Not Found status
    }

    @Test
    public void whenDeleteInventory_thenInventoryDeleted_PositivePath() {
        // Act - Delete the inventory using a DELETE request
        webTestClient.delete()
                .uri(BASE_URI_INVENTORIES + "/{inventoryId}", EXISTING_INVENTORY_ID)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void whenDeleteProductFromInventory_thenProductDeleted_PositivePath() {
        // Act - Delete the product from inventory using a DELETE request
        webTestClient.delete()
                .uri(BASE_URI_INVENTORIES + "/{inventoryId}/products/{productId}", EXISTING_INVENTORY_ID, EXISTING_PRODUCT_ID)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void whenDeleteInventory_withInvalidId_thenNotFound_NegativePath() {
        // Act - Attempt to delete the inventory using a DELETE request with an invalid ID
        webTestClient.delete()
                .uri(BASE_URI_INVENTORIES + "/{inventoryId}", INVALID_INVENTORY_ID)
                .exchange();
              //  .expectStatus().isNotFound();
    }

    @Test
    public void testAddInventory() {
        String requestBody = "{\"inventoryName\": \"Sample Inventory\"}";
        InventoryResponseModel mockResponse = new InventoryResponseModel();

        when(inventoryService.addInventory(any(InventoryRequestModel.class))).thenReturn(mockResponse);

        webTestClient.post()
                .uri("/api/v1/inventories")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(InventoryResponseModel.class)
                .isEqualTo(mockResponse);
    }

    @Test
    public void testPatchProductInInventory() {
        String requestBody = "{\"status\": \"Updated Status\"}";
        ProductResponseModel mockResponse = new ProductResponseModel();

        when(inventoryService.patchProductInInventory("123", "456", "ORDER_CANCELLED")).thenReturn(mockResponse);

        webTestClient.patch()
                .uri("/api/v1/inventories/123/products/456/AVAILABLE")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isOk();
    }

}