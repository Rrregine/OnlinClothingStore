package com.onlineclothingstore.inventory.dataaccesslayer.inventory;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InventoryRepositoryIntegrationTest {

    private final String FOUND_INVENTORY_ID = "5e6f3151-3a68-4b2c-ba12-c49e2d60e81f";

    @Autowired
    private InventoryRepository inventoryRepository;

    @MockBean
    private Inventory inventoryMock;

    @Test
    public void testFindByInventoryId_ExistingId_ShouldReturnInventory() {
        // Arrange
        Inventory inventory = new Inventory();
        inventory.setInventoryIdentifier(new InventoryIdentifier("123"));
        inventoryRepository.save(inventory);

        // Act
        Inventory foundInventory = inventoryRepository.findByInventoryIdentifier_InventoryId("123");

        // Assert
        assertEquals("123", foundInventory.getInventoryIdentifier().getInventoryId());
    }

    @Test
    public void testExistsByInventoryId_ExistingId_ShouldReturnTrue() {
        // Arrange
        Inventory inventory = new Inventory();
        inventory.setInventoryIdentifier(new InventoryIdentifier("456"));
        inventoryRepository.save(inventory);

        // Act
        boolean exists = inventoryRepository.existsByInventoryIdentifier_InventoryId("456");

        // Assert
        assertTrue(exists);
    }

    @Test
    public void testExistsByInventoryId_NonExistingId_ShouldReturnFalse() {
        // Arrange - No need to save any inventory with this ID

        // Act
        boolean exists = inventoryRepository.existsByInventoryIdentifier_InventoryId("789");

        // Assert
        assertTrue(!exists);
    }

    @Test
    public void testFindByInventoryId_NonExistingId_ShouldReturnNull() {
        // Arrange

        // Act
        Inventory foundInventory = inventoryRepository.findByInventoryIdentifier_InventoryId("456");

        // Assert
        assertNull(foundInventory);
    }

    @Test
    public void testFindAllByDepartmentId_ExistingId_ShouldReturnInventories() {
        // Arrange
        Inventory inventory1 = new Inventory();
        inventory1.setInventoryIdentifier(new InventoryIdentifier("dept1"));
        inventoryRepository.save(inventory1);

        Inventory inventory2 = new Inventory();
        inventory2.setInventoryIdentifier(new InventoryIdentifier("dept2"));
        inventoryRepository.save(inventory2);

        // Act
        List<Inventory> inventories = inventoryRepository.findAll();

        // Assert
        assertEquals(2, inventories.size());
        assertEquals("dept1", inventories.get(0).getInventoryIdentifier().getInventoryId());
    }

    @Test
    public void testFindAllByDepartmentId_NonExistingId_ShouldReturnEmptyList() {
        // Arrange

        // Act
        List<Inventory> inventories = inventoryRepository.findAll();
        // Assert
        assertEquals(0, inventories.size());
    }


}