package com.onlineclothingstore.apigateway.businesslayer.clients;

import com.onlineclothingstore.apigateway.presentationlayer.clients.ClientRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.clients.ClientResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<ClientResponseModel> getAllClients();
    ClientResponseModel getClientByClientId(String clientId);
    ClientResponseModel addClient(ClientRequestModel clientRequestModel);
    ClientResponseModel updateClient(ClientRequestModel updatedClient, String clientId);
    void deleteClient(String clientId);
}
