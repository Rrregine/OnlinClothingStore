package com.onlineclothingstore.apigateway.mappinglayer.inventory;

import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryController;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryResponseModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.product.ProductResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductResponseMapper {

    ProductResponseModel responseModelToResponseModel(ProductResponseModel productResponseModel);

    List<ProductResponseModel> responseModelListToResponseModelList(List<ProductResponseModel> productList);

    @AfterMapping
    default void addLinks(@MappingTarget ProductResponseModel productResponseModel){

        Link allLink = linkTo(methodOn(InventoryController.class)
                .getProductsByInventory(productResponseModel.getInventoryId()))
                .withSelfRel();
        productResponseModel.add(allLink);

        Link selfLink = linkTo(methodOn(InventoryController.class)
                .getInventoryByInventoryId(productResponseModel.getProductId()))
                .withSelfRel();
        productResponseModel.add(selfLink);
    }
}
