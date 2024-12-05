package com.onlineclothingstore.sales.businesslayer;

import com.onlineclothingstore.sales.presentationlayer.SaleRequestModel;
import com.onlineclothingstore.sales.presentationlayer.SaleResponseModel;
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
