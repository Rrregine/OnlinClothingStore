package com.onlineclothingstore.inventory.mapperlayer.product;

import com.onlineclothingstore.inventory.dataaccesslayer.inventory.InventoryIdentifier;
import com.onlineclothingstore.inventory.dataaccesslayer.product.ProductIdentifier;
import com.onlineclothingstore.inventory.dataaccesslayer.product.Product;
import com.onlineclothingstore.inventory.presentationlayer.product.ProductRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    @Mapping(target = "id", ignore = true)
    Product requestModelToEntity(ProductRequestModel productRequestModel,
                                 ProductIdentifier productIdentifier,
                                 InventoryIdentifier inventoryIdentifier);
}
