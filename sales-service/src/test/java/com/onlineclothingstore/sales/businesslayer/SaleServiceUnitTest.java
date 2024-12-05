package com.onlineclothingstore.sales.businesslayer;

import com.onlineclothingstore.sales.dataaccesslayer.SaleRepository;
import com.onlineclothingstore.sales.domainclientlayer.clients.ClientModel;
import com.onlineclothingstore.sales.domainclientlayer.clients.ClientServiceClient;
import com.onlineclothingstore.sales.domainclientlayer.employees.EmployeeModel;
import com.onlineclothingstore.sales.domainclientlayer.employees.EmployeeServiceClient;
import com.onlineclothingstore.sales.domainclientlayer.inventory.InventoryModel;
import com.onlineclothingstore.sales.domainclientlayer.inventory.InventoryServiceClient;
import com.onlineclothingstore.sales.domainclientlayer.inventory.ProductModel;
import com.onlineclothingstore.sales.mapperlayer.SaleResponseMapper;
import com.onlineclothingstore.sales.presentationlayer.SaleRequestModel;
import com.onlineclothingstore.sales.presentationlayer.SaleResponseModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration")
@ActiveProfiles("test")
class SaleServiceUnitTest {

    @Autowired
    SaleService saleService;

    @MockBean
    ClientServiceClient clientServiceClient;

    @MockBean
    InventoryServiceClient inventoryServiceClient;

    @MockBean
    EmployeeServiceClient employeesServiceClient;

    @MockBean
    SaleRepository saleRepository;


  /*  @Test
    public void whenValidClientId_EmployeeId_InventoryId_ProductId_thenProcessClientOrderRequest() {
        // Arrange
        var clientId = "10d84b69-7d92-4e3b-89e1-f5b124e24935";
        var employeeId = "e5913a79-9b1e-4516-9ffd-06578e7af261";
        var inventoryId = "8e7a1530-c9ae-4674-8e85-199457cc5c62";
        var productId = "product10";

        var saleRequestModel = SaleRequestModel.builder()
                .inventoryId(inventoryId)
                .productId(productId)
                .employeeId(employeeId)
                .build();

        var clientModel = ClientModel.builder()
                .clientId(clientId)
                .username("SarahDavis")
                .build();

        var employeeModel = EmployeeModel.builder()
                .employeeId(employeeId)
                .firstName("Vilma")
                .lastName("Chawner")
                .build();

        var inventoryModel = InventoryModel.builder()
                .inventoryId(inventoryId)
                .type("clothing")
                .build();

        var productModel = ProductModel.builder()
                .productId(productId)
                .build();

        // Define mock behaviors for service clients
        when(clientServiceClient.getClientByClientId(clientId)).thenReturn(clientModel);
        when(employeesServiceClient.getEmployeeByEmployeeId(employeeId)).thenReturn(employeeModel);
        when(inventoryServiceClient.getInventoryByInventoryId(inventoryId)).thenReturn(inventoryModel);
        when(inventoryServiceClient.getProductByProductIdAndInventoryId(productId, inventoryId)).thenReturn(productModel);

        // Act
        SaleResponseModel saleResponseModel = saleService.addClientOrder(saleRequestModel, clientId);

        // Assert
        assertNotNull(saleResponseModel);
    }*/
}