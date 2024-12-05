package com.onlineclothingstore.apigateway.mappinglayer.clients;

import com.onlineclothingstore.apigateway.presentationlayer.clients.ClientController;
import com.onlineclothingstore.apigateway.presentationlayer.clients.ClientResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ClientResponseMapper {

    ClientResponseModel responseModelToResponseModel(ClientResponseModel clientResponseModel);

    List<ClientResponseModel> responseModelListToResponseModelList(List<ClientResponseModel> clientResponseModels);

    @AfterMapping
    default void addLinks(@MappingTarget ClientResponseModel clientResponseModel){

        Link allLink = linkTo(methodOn(ClientController.class)
                .getAllClients())
                .withSelfRel();
        clientResponseModel.add(allLink);

        Link selfLink = linkTo(methodOn(ClientController.class)
                .getClientByClientId(clientResponseModel.getClientId()))
                .withSelfRel();
        clientResponseModel.add(selfLink);
    }


}
