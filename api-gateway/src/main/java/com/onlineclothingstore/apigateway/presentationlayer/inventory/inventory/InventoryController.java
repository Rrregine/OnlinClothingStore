package com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory;

import com.onlineclothingstore.apigateway.businesslayer.inventory.InventoryService;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.product.ProductRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.product.ProductResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @GetMapping()
    public ResponseEntity<List<InventoryResponseModel>> getAllInventories(){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getInventories());
    }

    @GetMapping("{inventoryId}")
    public ResponseEntity<InventoryResponseModel> getInventoryByInventoryId(@PathVariable String inventoryId){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getInventoryByInventoryId(inventoryId));
    }

    @GetMapping("{inventoryId}/products")
    public ResponseEntity<List<ProductResponseModel>> getProductsByInventory(@PathVariable String inventoryId){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getAllProductsByInventoryId(inventoryId));
    }

    @GetMapping("{inventoryId}/products/{productId}")
    public ResponseEntity<ProductResponseModel> getProductsByInventory(@PathVariable String inventoryId,
                                                                       @PathVariable String productId){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getProductByInventoryIdAndProductId(inventoryId, productId));
    }

    @PostMapping
    public ResponseEntity<InventoryResponseModel> addInventory(@RequestBody InventoryRequestModel inventoryRequestModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.addInventory(inventoryRequestModel));
    }

    @PostMapping("{inventoryId}/products")
    public ResponseEntity<ProductResponseModel> addProductToInventor(@RequestBody ProductRequestModel productRequestModel,
                                                                     @PathVariable String inventoryId){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.addProductToInventory(productRequestModel, inventoryId));
    }

    @PutMapping("{inventoryId}")
    public ResponseEntity<InventoryResponseModel> updateInventory(@RequestBody InventoryRequestModel inventoryRequestModel,
                                                                  @PathVariable String inventoryId){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.updateInventory(inventoryRequestModel, inventoryId));
    }

    @PutMapping("{inventoryId}/products/{productId}")
    public ResponseEntity<ProductResponseModel> updateProductInInventory(@RequestBody ProductRequestModel productRequestModel,
                                                                         @PathVariable String inventoryId,
                                                                         @PathVariable String productId){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.updateProductInInventory(productRequestModel, inventoryId, productId));
    }

    @DeleteMapping("{inventoryId}")
    public ResponseEntity<Void> deleteInventory(@PathVariable String inventoryId){
        inventoryService.deleteInventory(inventoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("{inventoryId}/products/{productId}")
    public ResponseEntity<Void> deleteVehicleFromInventory(@PathVariable String inventoryId,
                                                           @PathVariable String productId){
        inventoryService.deleteProductFromInventory(inventoryId, productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
