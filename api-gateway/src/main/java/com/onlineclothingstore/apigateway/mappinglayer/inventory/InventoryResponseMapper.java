package com.onlineclothingstore.apigateway.mappinglayer.inventory;

import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryController;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface InventoryResponseMapper {

    InventoryResponseModel responseModelToResponseModel(InventoryResponseModel inventoryResponseModel);

    List<InventoryResponseModel> responseModelListToResponseModelList(List<InventoryResponseModel> inventories);

    @AfterMapping
    default void addLinks(@MappingTarget InventoryResponseModel inventoryResponseModel){

        Link allLink = linkTo(methodOn(InventoryController.class)
                .getAllInventories())
                .withSelfRel();
        inventoryResponseModel.add(allLink);

        Link selfLink = linkTo(methodOn(InventoryController.class)
                .getInventoryByInventoryId(inventoryResponseModel.getInventoryId()))
                .withSelfRel();
        inventoryResponseModel.add(selfLink);
    }
}