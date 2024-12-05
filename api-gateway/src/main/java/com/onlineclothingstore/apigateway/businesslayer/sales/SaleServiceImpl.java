package com.onlineclothingstore.apigateway.businesslayer.sales;

import com.onlineclothingstore.apigateway.domainclientlayer.sales.SalesServiceClient;
import com.onlineclothingstore.apigateway.mappinglayer.sales.SaleResponseMapper;
import com.onlineclothingstore.apigateway.presentationlayer.sales.SaleRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.sales.SaleResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{

    private final SaleResponseMapper saleResponseMapper;
    private final SalesServiceClient salesServiceClient;

    public SaleServiceImpl(SaleResponseMapper saleResponseMapper,
                           SalesServiceClient salesServiceClient) {
        this.saleResponseMapper = saleResponseMapper;
        this.salesServiceClient = salesServiceClient;
    }

    @Override
    public List<SaleResponseModel> getAllClientOrdersByClient(String clientId) {
        return saleResponseMapper.responseModelListToResponseModelList(salesServiceClient.getAllClientOrdersByClient(clientId));
    }
    @Override
    public SaleResponseModel getClientOrderBySaleId(String clientId, String saleId) {
        return saleResponseMapper.responseModelToResponseModel(salesServiceClient.getClientOrderBySaleId(clientId, saleId));
    }

    @Override
    public SaleResponseModel addClientOrder(SaleRequestModel saleRequestModel, String clientId) {
        return saleResponseMapper.responseModelToResponseModel(salesServiceClient.addClientOrder(saleRequestModel, clientId));
    }

    @Override
    public SaleResponseModel updateClientOrder(SaleRequestModel saleRequestModel, String clientId, String saleId) {
        return saleResponseMapper.responseModelToResponseModel(salesServiceClient.updateClientOrder(saleRequestModel, clientId, saleId));
    }
    @Override
    public void deleteClientOrderFromClient(String clientId, String saleId) {
        salesServiceClient.deleteClientOrderFromClient(clientId, saleId);
    }
}
