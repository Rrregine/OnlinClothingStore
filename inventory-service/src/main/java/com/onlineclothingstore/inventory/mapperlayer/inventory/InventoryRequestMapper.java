package com.onlineclothingstore.inventory.mapperlayer.inventory;

import com.onlineclothingstore.inventory.dataaccesslayer.inventory.InventoryIdentifier;
import com.onlineclothingstore.inventory.dataaccesslayer.inventory.Inventory;
import com.onlineclothingstore.inventory.presentationlayer.inventory.InventoryRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventoryIdentifier", ignore = true)
    Inventory requestModelToEntity(InventoryRequestModel inventoryRequestModel,
                                   InventoryIdentifier inventoryIdentifier);
}
