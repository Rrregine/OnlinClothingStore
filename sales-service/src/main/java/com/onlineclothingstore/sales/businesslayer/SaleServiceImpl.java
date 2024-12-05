package com.onlineclothingstore.sales.businesslayer;

import com.onlineclothingstore.sales.dataaccesslayer.Sale;
import com.onlineclothingstore.sales.dataaccesslayer.SaleIdentifier;
import com.onlineclothingstore.sales.dataaccesslayer.SaleRepository;
import com.onlineclothingstore.sales.dataaccesslayer.SalesStatus;
import com.onlineclothingstore.sales.domainclientlayer.clients.ClientModel;
import com.onlineclothingstore.sales.domainclientlayer.clients.ClientServiceClient;
import com.onlineclothingstore.sales.domainclientlayer.employees.EmployeeModel;
import com.onlineclothingstore.sales.domainclientlayer.employees.EmployeeServiceClient;
import com.onlineclothingstore.sales.domainclientlayer.inventory.InventoryModel;
import com.onlineclothingstore.sales.domainclientlayer.inventory.InventoryServiceClient;
import com.onlineclothingstore.sales.domainclientlayer.inventory.ProductModel;
import com.onlineclothingstore.sales.domainclientlayer.inventory.Status;
import com.onlineclothingstore.sales.mapperlayer.SaleRequestMapper;
import com.onlineclothingstore.sales.mapperlayer.SaleResponseMapper;
import com.onlineclothingstore.sales.presentationlayer.SaleRequestModel;
import com.onlineclothingstore.sales.presentationlayer.SaleResponseModel;
import com.onlineclothingstore.sales.utils.exceptions.InvalidInputException;
import com.onlineclothingstore.sales.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;
    private final SaleResponseMapper saleResponseMapper;
    private final SaleRequestMapper saleRequestMapper;
    private final InventoryServiceClient inventoryServiceClient;
    private final ClientServiceClient clientServiceClient;
    private final EmployeeServiceClient employeeServiceClient;

    public SaleServiceImpl(SaleRepository saleRepository,
                           SaleResponseMapper saleResponseMapper,
                           SaleRequestMapper saleRequestMapper,
                           InventoryServiceClient inventoryServiceClient,
                           ClientServiceClient clientServiceClient,
                           EmployeeServiceClient employeeServiceClient) {
        this.saleRepository = saleRepository;
        this.saleResponseMapper = saleResponseMapper;
        this.saleRequestMapper = saleRequestMapper;
        this.inventoryServiceClient = inventoryServiceClient;
        this.clientServiceClient = clientServiceClient;
        this.employeeServiceClient = employeeServiceClient;
    }

    @Override
    public List<SaleResponseModel> getAllClientOrdersByClient(String clientId) {

        ClientModel clientModel = clientServiceClient.getClientByClientId(clientId);

        if (clientModel == null){
            throw new InvalidInputException("ClientId provided is invalid: " + clientId);
        }

        List<Sale> clientOrders = saleRepository.findSalesByClientModel_ClientId(clientId);

        return saleResponseMapper.entityListToResponseModelList(clientOrders);
    }

    @Override
    public SaleResponseModel getClientOrderBySaleId(String clientId, String saleId) {

        ClientModel clientModel = clientServiceClient.getClientByClientId(clientId);

        if (clientModel == null){
            throw new InvalidInputException("ClientId provided is invalid: " + clientId);
        }
        //verify sale exists
        Sale sale = saleRepository.findSaleByClientModel_ClientIdAndSaleIdentifier_SaleId(clientId, saleId);

        if(sale == null){
            throw new NotFoundException("Unknown saleId provided: " + saleId);
        }

        return saleResponseMapper.entityToResponseModel(sale);
    }

    @Override
    public SaleResponseModel addClientOrder(SaleRequestModel saleRequestModel, String clientId) {

        ClientModel foundClient = clientServiceClient.getClientByClientId(clientId);

        if (foundClient == null){
            throw new InvalidInputException("ClientId provided is invalid: " + clientId);
        }

        EmployeeModel foundEmployee = employeeServiceClient.getEmployeeByEmployeeId(saleRequestModel.getEmployeeId());

        if (foundEmployee == null){
            throw new InvalidInputException("EmployeeId provided is invalid: " + saleRequestModel.getEmployeeId());
        }

        InventoryModel foundInventory = inventoryServiceClient.getInventoryByInventoryId(saleRequestModel.getInventoryId());

        if (foundInventory == null){
            throw new InvalidInputException("InventoryId provided is invalid: " + saleRequestModel.getInventoryId());
        }

        ProductModel foundProduct = inventoryServiceClient.getProductByProductIdAndInventoryId(saleRequestModel.getInventoryId(), saleRequestModel.getProductId());

        if (foundProduct == null){
            throw new InvalidInputException("ProductId provided is invalid: " + saleRequestModel.getProductId());
        }

        if(!foundProduct.getStatus().equals("AVAILABLE")){
            throw new InvalidInputException("Product is not available for your order: " + saleRequestModel.getProductId());
        }

        Sale sale = saleRequestMapper.requestModelToEntity(saleRequestModel, new SaleIdentifier(),
                foundProduct, foundEmployee, foundClient);

        Sale savedSale = saleRepository.save(sale);

        if(savedSale.getSalesStatus().name().equals("ORDER_CANCELLED")){
            inventoryServiceClient.patchProductByInventoryId_ProductId(saleRequestModel.getInventoryId(), saleRequestModel.getProductId(), "AVAILABLE");
        }

        return saleResponseMapper.entityToResponseModel(savedSale);
    }

    @Override
    public SaleResponseModel updateClientOrder(SaleRequestModel saleRequestModel, String clientId, String saleId) {

        Sale foundSale = saleRepository.findSaleByClientModel_ClientIdAndSaleIdentifier_SaleId(clientId, saleId);

        if(foundSale == null){
            throw new NotFoundException("Unknown saleId provided: " + saleId);
        }

        ClientModel foundClient = clientServiceClient.getClientByClientId(clientId);

        if (foundClient == null){
            throw new InvalidInputException("ClientId provided is invalid: " + clientId);
        }

        EmployeeModel foundEmployee = employeeServiceClient.getEmployeeByEmployeeId(saleRequestModel.getEmployeeId());

        if (foundEmployee == null){
            throw new InvalidInputException("EmployeeId provided is invalid: " + saleRequestModel.getEmployeeId());
        }

        ProductModel foundProduct = inventoryServiceClient.getProductByProductIdAndInventoryId(saleRequestModel.getInventoryId(), saleRequestModel.getProductId());

        if (foundProduct == null){
            throw new InvalidInputException("InventoryId provided is invalid: " + saleRequestModel.getInventoryId());
        }

        if(!foundProduct.getStatus().equals("AVAILABLE")){
            throw new InvalidInputException("Product is not available for your order: " + saleRequestModel.getProductId());
        }

        Sale sale = saleRequestMapper.requestModelToEntity(saleRequestModel, foundSale.getSaleIdentifier(),
                foundProduct, foundEmployee, foundClient);

        sale.setId(foundSale.getId());

        Sale savedSale = saleRepository.save(sale);

        if(savedSale.getSalesStatus().name().equals("ORDER_CANCELLED")){
            inventoryServiceClient.patchProductByInventoryId_ProductId(saleRequestModel.getInventoryId(), saleRequestModel.getProductId(), "AVAILABLE");
        }

        return saleResponseMapper.entityToResponseModel(savedSale);
    }

    @Override
    public void deleteClientOrderFromClient(String clientId, String saleId) {

        ClientModel foundClient = clientServiceClient.getClientByClientId(clientId);

        if (foundClient == null){
            throw new InvalidInputException("ClientId provided is invalid: " + clientId);
        }

        Sale existingSale = saleRepository.findSaleByClientModel_ClientIdAndSaleIdentifier_SaleId(clientId, saleId);

        if (existingSale == null) {
            throw new NotFoundException("Unknown sale identifier provided: " + saleId);
        }

        saleRepository.delete(existingSale);
    }
}
