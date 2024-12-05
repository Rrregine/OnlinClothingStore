package com.onlineclothingstore.clients.mapperlayer;

import com.onlineclothingstore.clients.dataaccesslayer.Client;
import com.onlineclothingstore.clients.presentationlayer.ClientController;
import com.onlineclothingstore.clients.presentationlayer.ClientResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientResponseMapper {

    @Mapping(expression = "java(client.getClientIdentifier().getClientId())", target = "clientId")
    @Mapping(expression = "java(client.getAddress().getStreetAddress())", target = "streetAddress")
    @Mapping(expression = "java(client.getAddress().getCity())", target = "city")
    @Mapping(expression = "java(client.getAddress().getProvince())", target = "province")
    @Mapping(expression = "java(client.getAddress().getCountry())", target = "country")
    @Mapping(expression = "java(client.getAddress().getPostalCode())", target = "postalCode")
    ClientResponseModel entityToResponseModel(Client client);

    List<ClientResponseModel> entityListToResponseModelList(List<Client> clients);

    /*
    @AfterMapping
    default void addLinks(@MappingTarget ClientResponseModel model, Client client) {
        //self link
        Link selfLink = linkTo(methodOn(ClientController.class)
                .getClientByClientId(model.getClientId()))
                .withSelfRel();
        model.add(selfLink);
    } */
}

