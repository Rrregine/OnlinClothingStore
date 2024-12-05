package com.onlineclothingstore.sales.mapperlayer;

import com.onlineclothingstore.sales.dataaccesslayer.Sale;
import com.onlineclothingstore.sales.presentationlayer.SaleResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleResponseMapper {

    @Mapping(expression = "java(sale.getSaleIdentifier().getSaleId())", target = "saleId")

    @Mapping(expression = "java(sale.getProductModel().getInventoryId())", target = "inventoryId")
    @Mapping(expression = "java(sale.getProductModel().getProductId())", target = "productId")
    @Mapping(expression = "java(sale.getProductModel().getSize())", target = "productSize")
    @Mapping(expression = "java(sale.getProductModel().getModel())", target = "productModel")
    @Mapping(expression = "java(sale.getProductModel().getImage())", target = "productImage")
    @Mapping(expression = "java(sale.getProductModel().getStatus())", target = "productStatus")
//    @Mapping(expression = "java(sale.getProductModel().getCountry())", target = "productCountry")
//    @Mapping(expression = "java(sale.getProductModel().getBrand())", target = "productBrand")
    @Mapping(expression = "java(sale.getProductModel().getOptionsList())", target = "productOptionList")

    @Mapping(expression = "java(sale.getClientModel().getClientId())", target = "clientId")
    @Mapping(expression = "java(sale.getClientModel().getUsername())", target = "clientUsername")

    @Mapping(expression = "java(sale.getEmployeeModel().getEmployeeId())", target = "employeeId")
    @Mapping(expression = "java(sale.getEmployeeModel().getFirstName())", target = "employeeFirstName")
    @Mapping(expression = "java(sale.getEmployeeModel().getLastName())", target = "employeeLastName")

    SaleResponseModel entityToResponseModel(Sale sale);

    List<SaleResponseModel> entityListToResponseModelList(List<Sale> sales);
}
