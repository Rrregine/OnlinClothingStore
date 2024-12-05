package com.onlineclothingstore.clients.businesslayer;

import com.onlineclothingstore.clients.presentationlayer.ClientRequestModel;
import com.onlineclothingstore.clients.presentationlayer.ClientResponseModel;

import java.util.List;

public interface ClientService {

    List<ClientResponseModel> getClients();
    ClientResponseModel getClientByClientId(String clientId);
    ClientResponseModel addClient(ClientRequestModel customerRequestModel);
    ClientResponseModel updateClient(ClientRequestModel updatedClient, String clientId);
    void deleteClient(String clientId);
}
