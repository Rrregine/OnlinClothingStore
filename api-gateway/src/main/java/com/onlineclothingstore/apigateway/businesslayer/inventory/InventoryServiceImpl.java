package com.onlineclothingstore.apigateway.businesslayer.inventory;

import com.onlineclothingstore.apigateway.domainclientlayer.inventory.inventory.InventoryServiceClient;
import com.onlineclothingstore.apigateway.mappinglayer.inventory.InventoryResponseMapper;
import com.onlineclothingstore.apigateway.mappinglayer.inventory.ProductResponseMapper;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryResponseModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.product.ProductRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.product.ProductResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{

    private final InventoryResponseMapper inventoryResponseMapper;
    private final ProductResponseMapper productResponseMapper;
    private final InventoryServiceClient inventoryServiceClient;

    public InventoryServiceImpl(InventoryResponseMapper inventoryResponseMapper,
                                ProductResponseMapper productResponseMapper,
                                InventoryServiceClient inventoryServiceClient) {
        this.inventoryResponseMapper = inventoryResponseMapper;
        this.productResponseMapper = productResponseMapper;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @Override
    public List<InventoryResponseModel> getInventories() {
        return inventoryResponseMapper.responseModelListToResponseModelList(inventoryServiceClient.getAllInventories());
    }

    @Override
    public InventoryResponseModel getInventoryByInventoryId(String inventoryId) {
        return inventoryResponseMapper.responseModelToResponseModel(inventoryServiceClient.getInventoryByInventoryId(inventoryId));
    }

    @Override
    public InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel) {
        return inventoryResponseMapper.responseModelToResponseModel(inventoryServiceClient.addInventory(inventoryRequestModel));
    }

    @Override
    public InventoryResponseModel updateInventory(InventoryRequestModel inventoryRequestModel, String inventoryId) {
        return inventoryResponseMapper.responseModelToResponseModel(inventoryServiceClient.updateInventoryByInventoryId(inventoryRequestModel, inventoryId));
    }

    @Override
    public void deleteInventory(String inventoryId) {
        inventoryServiceClient.deleteInventoryByInventoryId(inventoryId);
    }

    @Override
    public List<ProductResponseModel> getAllProductsByInventoryId(String inventoryId) {
        return productResponseMapper.responseModelListToResponseModelList(inventoryServiceClient.getAllProductsByInventory(inventoryId));
    }

    @Override
    public ProductResponseModel getProductByInventoryIdAndProductId(String inventoryId, String productId) {
        return productResponseMapper.responseModelToResponseModel(inventoryServiceClient.getProductByInventoryIdAndProductId(inventoryId, productId));
    }

    @Override
    public ProductResponseModel addProductToInventory(ProductRequestModel productRequestModel, String inventoryId) {
        return productResponseMapper.responseModelToResponseModel(inventoryServiceClient.addProduct(productRequestModel, inventoryId));
    }

    @Override
    public ProductResponseModel updateProductInInventory(ProductRequestModel productRequestModel, String inventoryId, String productId) {
        return productResponseMapper.responseModelToResponseModel(inventoryServiceClient.updateProductByInventoryIdAndProductId(productRequestModel, inventoryId, productId));
    }

    @Override
    public void deleteProductFromInventory(String inventoryId, String productId) {
        inventoryServiceClient.deleteProductByInventoryIdAndProductId(inventoryId, productId);
    }
}
