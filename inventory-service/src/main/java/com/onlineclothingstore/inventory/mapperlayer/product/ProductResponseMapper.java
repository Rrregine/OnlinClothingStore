package com.onlineclothingstore.inventory.mapperlayer.product;

import com.onlineclothingstore.inventory.dataaccesslayer.product.Product;
import com.onlineclothingstore.inventory.presentationlayer.product.ProductResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    @Mapping(expression = "java(product.getProductIdentifier().getProductId())", target = "productId")
    @Mapping(expression = "java(product.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
//    @Mapping(expression = "java(product.getManufacturer().getBrand())", target = "brand")
//    @Mapping(expression = "java(product.getManufacturer().getCountry())", target = "country")

    ProductResponseModel entityToResponseModel(Product product);

    List<ProductResponseModel> entityListToResponseModelList(List<Product> productList);
}
