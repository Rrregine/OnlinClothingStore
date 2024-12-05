package com.onlineclothingstore.inventory.mapperlayer.inventory;

import com.onlineclothingstore.inventory.dataaccesslayer.inventory.Inventory;
import com.onlineclothingstore.inventory.presentationlayer.inventory.InventoryResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel ="spring")
public interface InventoryResponseMapper {

    @Mapping(expression = "java(inventory.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    InventoryResponseModel entityToResponseModel(Inventory inventory);

    List<InventoryResponseModel> entityListToResponseModelList(List<Inventory> inventories);

}