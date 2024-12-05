package com.onlineclothingstore.apigateway.businesslayer.inventory;

import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryResponseModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.product.ProductRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.product.ProductResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {

    List<InventoryResponseModel> getInventories();
    InventoryResponseModel getInventoryByInventoryId(String inventoryId);
    InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel);
    InventoryResponseModel updateInventory(InventoryRequestModel inventoryRequestModel, String inventoryId);
    void deleteInventory(String inventoryId);

    List<ProductResponseModel> getAllProductsByInventoryId(String inventoryId);
    ProductResponseModel getProductByInventoryIdAndProductId(String inventoryId, String productId);
    ProductResponseModel addProductToInventory(ProductRequestModel productRequestModel, String inventoryId);
    ProductResponseModel updateProductInInventory(ProductRequestModel productRequestModel, String inventoryId, String productId);
    void deleteProductFromInventory(String inventoryId, String productId);
}
