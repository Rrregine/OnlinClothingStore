package com.onlineclothingstore.inventory.businesslayer;

import com.onlineclothingstore.inventory.presentationlayer.inventory.InventoryRequestModel;
import com.onlineclothingstore.inventory.presentationlayer.inventory.InventoryResponseModel;
import com.onlineclothingstore.inventory.presentationlayer.product.ProductRequestModel;
import com.onlineclothingstore.inventory.presentationlayer.product.ProductResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {

    List<InventoryResponseModel> getInventories();
    InventoryResponseModel getInventoryByInventoryId(String inventoryId);
    InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel);
    InventoryResponseModel updateInventory(InventoryRequestModel inventoryRequestModel, String inventoryId);
    void deleteInventory(String inventoryId);
    ProductResponseModel getProductByInventoryIdAndProductId(String inventoryId, String productId);
    List<ProductResponseModel> getAllProductsByInventoryId(String inventoryId);
    ProductResponseModel addProductToInventory(ProductRequestModel productRequestModel, String inventoryId);
    ProductResponseModel updateProductInInventory(ProductRequestModel productRequestModel, String inventoryId, String productId);
    void deleteProductFromInventory(String inventoryId, String productId);
    ProductResponseModel patchProductInInventory(String inventoryId, String productId, String status);
}
