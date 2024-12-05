package com.onlineclothingstore.apigateway.businesslayer.clients;

import com.onlineclothingstore.apigateway.domainclientlayer.clients.ClientsServiceClient;
import com.onlineclothingstore.apigateway.mappinglayer.clients.ClientResponseMapper;
import com.onlineclothingstore.apigateway.presentationlayer.clients.ClientRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.clients.ClientResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientsServiceClient clientsServiceClient;
    private final ClientResponseMapper clientResponseMapper;

    public ClientServiceImpl(ClientsServiceClient clientsServiceClient, ClientResponseMapper clientResponseMapper) {
        this.clientsServiceClient = clientsServiceClient;
        this.clientResponseMapper = clientResponseMapper;
    }

    @Override
    public List<ClientResponseModel> getAllClients() {
        return clientResponseMapper.responseModelListToResponseModelList(clientsServiceClient.getAllClients());
    }

    @Override
    public ClientResponseModel getClientByClientId(String clientId) {
        return clientResponseMapper.responseModelToResponseModel(clientsServiceClient.getClientByClientId(clientId));
    }

    @Override
    public ClientResponseModel addClient(ClientRequestModel clientRequestModel) {
        return clientResponseMapper.responseModelToResponseModel(clientsServiceClient.addClient(clientRequestModel));
    }

    @Override
    public ClientResponseModel updateClient(ClientRequestModel updatedClient, String clientId) {
        return clientResponseMapper.responseModelToResponseModel(clientsServiceClient.updateClientByClientId(updatedClient, clientId));
    }

    @Override
    public void deleteClient(String clientId) {
        clientsServiceClient.deleteClientByClientId(clientId);
    }
}
