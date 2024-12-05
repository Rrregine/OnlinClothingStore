package com.onlineclothingstore.sales.presentationlayer;

import com.onlineclothingstore.sales.businesslayer.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("{clientId}/orders/{saleId}")
    public ResponseEntity<SaleResponseModel> getClientOrderBySaleId(@PathVariable String clientId,
                                                                    @PathVariable String saleId){
        return ResponseEntity.ok().body(saleService.getClientOrderBySaleId(clientId, saleId));
    }

    @PostMapping
    public ResponseEntity<SaleResponseModel> addCustomerPurchase(@RequestBody SaleRequestModel saleRequestModel,
                                                                 @PathVariable String clientId) {
        return ResponseEntity.ok().body(saleService.addClientOrder(saleRequestModel, clientId));
    }

    @GetMapping("{clientId}/orders")
    public ResponseEntity<List<SaleResponseModel>> getAllClientOrdersByClient(@PathVariable String clientId){
        return ResponseEntity.ok().body(saleService.getAllClientOrdersByClient(clientId));
    }

    @PutMapping("{clientId}/orders/{saleId}")
    public ResponseEntity<SaleResponseModel> updateClientOrder(@RequestBody SaleRequestModel saleRequestModel,
                                                                         @PathVariable String clientId,
                                                                         @PathVariable String saleId){
        return ResponseEntity.status(HttpStatus.OK).body(saleService.updateClientOrder(saleRequestModel, clientId, saleId));
    }

    @DeleteMapping("{clientId}/orders/{saleId}")
    public ResponseEntity<Void> deleteClientOrderFromClient(@PathVariable String clientId,
                                                           @PathVariable String saleId){
        saleService.deleteClientOrderFromClient(clientId, saleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
