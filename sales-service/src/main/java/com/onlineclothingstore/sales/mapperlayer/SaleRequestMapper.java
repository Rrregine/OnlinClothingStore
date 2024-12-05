package com.onlineclothingstore.sales.mapperlayer;

import com.onlineclothingstore.sales.dataaccesslayer.Sale;
import com.onlineclothingstore.sales.dataaccesslayer.SaleIdentifier;
import com.onlineclothingstore.sales.domainclientlayer.clients.ClientModel;
import com.onlineclothingstore.sales.domainclientlayer.employees.EmployeeModel;
import com.onlineclothingstore.sales.domainclientlayer.inventory.ProductModel;
import com.onlineclothingstore.sales.presentationlayer.SaleRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleRequestMapper {

    @Mapping(target = "id", ignore = true)
    Sale requestModelToEntity(SaleRequestModel saleRequestModel,
                              SaleIdentifier saleIdentifier,
                              ProductModel productModel,
                              EmployeeModel employeeModel,
                              ClientModel clientModel);
}
