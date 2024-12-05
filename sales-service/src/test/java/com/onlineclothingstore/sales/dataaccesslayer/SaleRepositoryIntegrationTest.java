package com.onlineclothingstore.sales.dataaccesslayer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class SaleRepositoryIntegrationTest {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private MongoTemplate mongoTemplate; // Optional if you need direct access to MongoDB operations

    @Test
    public void whenSaveSale_thenSaleIsPersisted() {
        // Arrange
        Sale sale = new Sale(); // Create a Sale object

        // Act
        Sale savedSale = saleRepository.save(sale);

        // Assert
        assertTrue(saleRepository.existsById(savedSale.getId()));
    }

  /*  @Test
    public void givenSalesExist_whenFindSalesByClientId_thenReturnsCorrectSales() {
        // Arrange
        String clientId = "9a7f32d6-4286-4c63-af92-d57542607c3e";
        // Assuming there are sales related to this client ID in the database

        // Act
        List<Sale> sales = saleRepository.findSalesByClientModel_ClientId(clientId);

        // Assert
        assertFalse(sales.isEmpty());
        assertEquals(clientId, sales.get(0).getClientModel().getClientId());
    }

    @Test
    public void givenSaleExists_whenFindSaleByClientIdAndSaleId_thenReturnsCorrectSale() {
        // Arrange
        String clientId = "9a7f32d6-4286-4c63-af92-d57542607c3e";
        String saleId = "product10";
        // Assuming there is a sale with this client ID and sale ID in the database

        // Act
        Sale sale = saleRepository.findSaleByClientModel_ClientIdAndSaleIdentifier_SaleId(clientId, saleId);

        // Assert
        assertEquals(clientId, sale.getClientModel().getClientId());
        assertEquals(saleId, sale.getSaleIdentifier().getSaleId());
    } */

    @Test
    public void givenSaleExists_whenDeleteSaleBySaleId_thenSaleIsDeleted() {
        // Arrange
        String saleId = "product10";
        // Assuming there is a sale with this sale ID in the database

        // Act
        saleRepository.deleteSaleBySaleIdentifier_SaleId(saleId);

        // Assert
        assertFalse(saleRepository.existsSaleBySaleIdentifier_SaleId(saleId));
    }

    @Test
    public void givenSaleDoesNotExist_whenDeleteSaleBySaleId_thenSaleIsNotDeleted() {
        // Arrange
        String nonExistentSaleId = "nonExistentSaleId";

        // Act
        saleRepository.deleteSaleBySaleIdentifier_SaleId(nonExistentSaleId);

        // Assert
        assertFalse(saleRepository.existsSaleBySaleIdentifier_SaleId(nonExistentSaleId));
    }
}