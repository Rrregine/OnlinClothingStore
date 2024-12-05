package com.onlineclothingstore.inventory.dataaccesslayer.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Integer> {

    List<Product> findAllByInventoryIdentifier_InventoryId(String inventoryId);

    Product findByProductIdentifier_ProductId(String productId);

    Product findByInventoryIdentifier_InventoryIdAndProductIdentifier_ProductId(String inventoryId, String productId);
}
