package com.onlineclothingstore.apigateway.mappinglayer.sales;

import com.onlineclothingstore.apigateway.presentationlayer.sales.SaleController;
import com.onlineclothingstore.apigateway.presentationlayer.sales.SaleResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface SaleResponseMapper {

    SaleResponseModel responseModelToResponseModel(SaleResponseModel saleResponseModel);

    List<SaleResponseModel> responseModelListToResponseModelList(List<SaleResponseModel> sales);

    @AfterMapping
    default void addLinks(@MappingTarget SaleResponseModel saleResponseModel){

        Link allLink = linkTo(methodOn(SaleController.class)
                .getAllClientOrdersByClient(saleResponseModel.getClientId()))
                .withSelfRel();
        saleResponseModel.add(allLink);

        Link selfLink = linkTo(methodOn(SaleController.class)
                .getClientOrderBySaleId(saleResponseModel.getClientId(), saleResponseModel.getSaleId()))
                .withSelfRel();
        saleResponseModel.add(selfLink);
    }
}
