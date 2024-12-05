package com.onlineclothingstore.inventory.businesslayer;

import com.onlineclothingstore.inventory.dataaccesslayer.inventory.InventoryIdentifier;
import com.onlineclothingstore.inventory.dataaccesslayer.product.ProductIdentifier;
import com.onlineclothingstore.inventory.dataaccesslayer.inventory.Inventory;
import com.onlineclothingstore.inventory.dataaccesslayer.inventory.InventoryRepository;
import com.onlineclothingstore.inventory.dataaccesslayer.product.Product;
import com.onlineclothingstore.inventory.dataaccesslayer.product.ProductRepository;
import com.onlineclothingstore.inventory.dataaccesslayer.product.Status;
import com.onlineclothingstore.inventory.mapperlayer.inventory.InventoryRequestMapper;
import com.onlineclothingstore.inventory.mapperlayer.inventory.InventoryResponseMapper;
import com.onlineclothingstore.inventory.mapperlayer.product.ProductRequestMapper;
import com.onlineclothingstore.inventory.mapperlayer.product.ProductResponseMapper;
import com.onlineclothingstore.inventory.presentationlayer.inventory.InventoryRequestModel;
import com.onlineclothingstore.inventory.presentationlayer.inventory.InventoryResponseModel;
import com.onlineclothingstore.inventory.presentationlayer.product.ProductRequestModel;
import com.onlineclothingstore.inventory.presentationlayer.product.ProductResponseModel;
import com.onlineclothingstore.inventory.utils.exceptions.NotFoundException;
import com.onlineclothingstore.inventory.utils.exceptions.NullInventoryException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final InventoryResponseMapper inventoryResponseMapper;
    private final ProductRepository productRepository;
    private final ProductResponseMapper productResponseMapper;
    private final InventoryRequestMapper inventoryRequestMapper;
    private final ProductRequestMapper productRequestMapper;

    public InventoryServiceImpl(InventoryRepository inventoryRepository,
                                InventoryResponseMapper inventoryResponseMapper,
                                ProductRepository productRepository,
                                ProductResponseMapper productResponseMapper,
                                InventoryRequestMapper inventoryRequestMapper,
                                ProductRequestMapper productRequestMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryResponseMapper = inventoryResponseMapper;
        this.productRepository = productRepository;
        this.productResponseMapper = productResponseMapper;
        this.inventoryRequestMapper = inventoryRequestMapper;
        this.productRequestMapper = productRequestMapper;
    }

    @Override
    public List<InventoryResponseModel> getInventories() {
        List<Inventory> inventoryEntities = inventoryRepository.findAll();
        return inventoryResponseMapper.entityListToResponseModelList(inventoryEntities);
    }

    @Override
    public InventoryResponseModel getInventoryByInventoryId(String inventoryId) {
        Inventory foundInventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);

        if( foundInventory == null){
            throw new NotFoundException("Unknown inventoryId: " + inventoryId);
        }

        return inventoryResponseMapper.entityToResponseModel(foundInventory);
    }

    @Override
    public InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel) {

        Inventory inventory = inventoryRequestMapper.requestModelToEntity(inventoryRequestModel, new InventoryIdentifier());

        if (inventory.getType().length() > 20){
            throw new NullInventoryException("Type cannot be more than 20 characters!");
        }

        Inventory savedInventory = inventoryRepository.save(inventory);

        return inventoryResponseMapper.entityToResponseModel(savedInventory);
    }

    @Override
    public InventoryResponseModel updateInventory(InventoryRequestModel inventoryRequestModel, String inventoryId) {

        Inventory foundInventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);

        if (foundInventory == null){
            throw new NotFoundException("Unknown inventoryId : " + inventoryId);
        }

        Inventory inventory = inventoryRequestMapper.requestModelToEntity(inventoryRequestModel, foundInventory.getInventoryIdentifier());
        inventory.setId(foundInventory.getId());

        Inventory savedInventory = inventoryRepository.save(inventory);

        return inventoryResponseMapper.entityToResponseModel(savedInventory);
    }

    @Override
    public void deleteInventory(String inventoryId) {

        Inventory foundInventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);

        if (foundInventory == null){
            throw new NotFoundException("Unknown inventoryId : " + inventoryId);
        }

        inventoryRepository.delete(foundInventory);
    }

    @Override
    public ProductResponseModel getProductByInventoryIdAndProductId(String inventoryId, String productId) {

        Product product = productRepository.findByInventoryIdentifier_InventoryIdAndProductIdentifier_ProductId(inventoryId, productId);

        return productResponseMapper.entityToResponseModel(product);
    }

    @Override
    public List<ProductResponseModel> getAllProductsByInventoryId(String inventoryId) {

        List<Product> productEntities = productRepository.findAllByInventoryIdentifier_InventoryId(inventoryId);

        return productResponseMapper.entityListToResponseModelList(productEntities);
    }

    @Override
    public ProductResponseModel addProductToInventory(ProductRequestModel productRequestModel, String inventoryId) {

        Product product = productRequestMapper.requestModelToEntity(productRequestModel, new ProductIdentifier(), new InventoryIdentifier(productRequestModel.getInventoryId()));

        Product savedProduct = productRepository.save(product);

        return productResponseMapper.entityToResponseModel(savedProduct);
    }

    @Override
    public ProductResponseModel updateProductInInventory(ProductRequestModel productRequestModel, String inventoryId, String productId) {

        Inventory existingInventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);

        if (existingInventory == null) {
            throw new NullInventoryException("There is no such an inventory");
        }

        Product foundProduct = productRepository.findByProductIdentifier_ProductId(productId);

        if (foundProduct == null){
            throw new NotFoundException("Unknown productId : " + productId);
        }

        Product product = productRequestMapper.requestModelToEntity(productRequestModel, foundProduct.getProductIdentifier(), new InventoryIdentifier(productRequestModel.getInventoryId()));
        product.setId(foundProduct.getId());
        Product savedProduct = productRepository.save(product);

        return productResponseMapper.entityToResponseModel(savedProduct);
    }

    @Override
    public void deleteProductFromInventory(String inventoryId, String productId) {

        if (!inventoryRepository.existsByInventoryIdentifier_InventoryId(inventoryId)) {
            throw new NotFoundException("Unknown inventoryId provided: " + inventoryId);
        }

        Product existingProduct = productRepository.findByProductIdentifier_ProductId(productId);

        if (existingProduct == null) {
            throw new NotFoundException("Unknown product identifier provided: " + productId);
        }

        productRepository.delete(existingProduct);
    }

    @Override
    public ProductResponseModel patchProductInInventory(String inventoryId, String productId, String status) {

        if (!inventoryRepository.existsByInventoryIdentifier_InventoryId(inventoryId)) {
            throw new NotFoundException("Unknown inventoryId provided: " + inventoryId);
        }

        Product existingProduct = productRepository.findByProductIdentifier_ProductId(productId);

        if (existingProduct == null) {
            throw new NotFoundException("Unknown product identifier provided: " + productId);
        }

        existingProduct.setStatus(Status.valueOf(status));

        Product savedProduct = productRepository.save(existingProduct);

        return productResponseMapper.entityToResponseModel(savedProduct);
    }
}
