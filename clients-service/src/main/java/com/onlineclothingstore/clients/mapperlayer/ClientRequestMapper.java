package com.onlineclothingstore.clients.mapperlayer;

import com.onlineclothingstore.clients.dataaccesslayer.Address;
import com.onlineclothingstore.clients.dataaccesslayer.Client;
import com.onlineclothingstore.clients.presentationlayer.ClientRequestModel;
import com.onlineclothingstore.clients.dataaccesslayer.ClientIdentifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClientRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Client requestModelToEntity(ClientRequestModel clientRequestModel, ClientIdentifier clientIdentifier,
                                Address address);
}
