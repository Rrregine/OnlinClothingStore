package com.onlineclothingstore.apigateway.businesslayer.sales;

import com.onlineclothingstore.apigateway.presentationlayer.sales.SaleRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.sales.SaleResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SaleService {

    List<SaleResponseModel> getAllClientOrdersByClient(String clientId);
    SaleResponseModel getClientOrderBySaleId(String clientId, String saleId);
    SaleResponseModel addClientOrder(SaleRequestModel saleRequestModel, String clientId);
    SaleResponseModel updateClientOrder (SaleRequestModel saleRequestModel, String clientId, String saleId);
    void deleteClientOrderFromClient(String clientId, String saleId);
}
