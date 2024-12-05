package com.onlineclothingstore.inventory.dataaccesslayer.product;

import com.onlineclothingstore.inventory.dataaccesslayer.inventory.InventoryIdentifier;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryIntegrationTest {

    private final String FOUND_INVENTORY_ID = "5e6f3151-3a68-4b2c-ba12-c49e2d60e81f";

    @Mock
    private ProductRepository productRepository;

   /* @Test
    public void testFindAllByInventoryId_ExistingId_ShouldReturnProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setInventoryIdentifier(new InventoryIdentifier(FOUND_INVENTORY_ID));
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setInventoryIdentifier(new InventoryIdentifier(FOUND_INVENTORY_ID));
        productRepository.save(product2);

        // Act
        List<Product> products = productRepository.findAllByInventoryIdentifier_InventoryId(FOUND_INVENTORY_ID);

        // Assert
        assertEquals(2, products.size());
        assertEquals(FOUND_INVENTORY_ID, products.get(0).getInventoryIdentifier().getInventoryId());
    }

    @Test
    public void testFindByProductId_ExistingId_ShouldReturnProduct() {
        // Arrange
        Product product = new Product();
        product.setProductIdentifier(new ProductIdentifier("123"));
        productRepository.save(product);

        // Act
        Product foundProduct = productRepository.findByProductIdentifier_ProductId("123");

        // Assert
        assertEquals("123", foundProduct.getProductIdentifier().getProductId());
    }

    @Test
    public void testFindByInventoryIdAndProductId_ExistingIds_ShouldReturnProduct() {
        // Arrange
        Product product = new Product();
        product.setInventoryIdentifier(new InventoryIdentifier("inv1"));
        product.setProductIdentifier(new ProductIdentifier("123"));
        productRepository.save(product);

        // Act
        Product foundProduct = productRepository.findByInventoryIdentifier_InventoryIdAndProductIdentifier_ProductId("inv1", "123");

        // Assert
        assertNotNull(foundProduct);
        assertEquals("inv1", foundProduct.getInventoryIdentifier().getInventoryId());
        assertEquals("123", foundProduct.getProductIdentifier().getProductId());
    }*/

    @Test
    public void testFindByProductId_NonExistingId_ShouldReturnNull() {
        // Arrange

        // Act
        Product foundProduct = productRepository.findByProductIdentifier_ProductId("456");

        // Assert
        assertNull(foundProduct);
    }

    @Test
    public void testFindAllByInventoryId_NonExistingId_ShouldReturnEmptyList() {
        // Arrange

        // Act
        List<Product> products = productRepository.findAllByInventoryIdentifier_InventoryId("inv3");

        // Assert
        assertEquals(0, products.size());
    }
}