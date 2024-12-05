package com.onlineclothingstore.clients.businesslayer;

import com.onlineclothingstore.clients.dataaccesslayer.Address;
import com.onlineclothingstore.clients.dataaccesslayer.Client;
import com.onlineclothingstore.clients.dataaccesslayer.ClientRepository;
import com.onlineclothingstore.clients.mapperlayer.ClientRequestMapper;
import com.onlineclothingstore.clients.mapperlayer.ClientResponseMapper;
import com.onlineclothingstore.clients.presentationlayer.ClientRequestModel;
import com.onlineclothingstore.clients.presentationlayer.ClientResponseModel;
import com.onlineclothingstore.clients.dataaccesslayer.ClientIdentifier;
import com.onlineclothingstore.clients.utils.exceptions.BadPostalCodeException;
import com.onlineclothingstore.clients.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final ClientResponseMapper clientResponseMapper;
    private final ClientRequestMapper clientRequestMapper;


    public ClientServiceImpl(ClientRepository clientRepository, ClientResponseMapper clientResponseMapper, ClientRequestMapper clientRequestMapper) {
        this.clientRepository = clientRepository;
        this.clientResponseMapper = clientResponseMapper;
        this.clientRequestMapper = clientRequestMapper;
    }

    @Override
    public List<ClientResponseModel> getClients() {
        List<Client> clients = clientRepository.findAll();
        return clientResponseMapper.entityListToResponseModelList(clients);
    }

    @Override
    public ClientResponseModel getClientByClientId(String clientId) {
        Client client = clientRepository.findByClientIdentifier_ClientId(clientId);

        if (client == null) {
            throw new NotFoundException("Unknown clientId: " + clientId);
        }
        return clientResponseMapper.entityToResponseModel(client);
    }

    @Override
    public ClientResponseModel addClient(ClientRequestModel clientRequestModel) {

        Address address = new Address(clientRequestModel.getStreetAddress(), clientRequestModel.getCity(),
                clientRequestModel.getProvince(), clientRequestModel.getCountry(),
                clientRequestModel.getPostalCode());

        if (clientRequestModel.getPostalCode().length() < 6) {
            throw new BadPostalCodeException("Postal code must be at least 6 characters long.");
        }

        Client client = clientRequestMapper.requestModelToEntity(clientRequestModel, new ClientIdentifier(), address);

        client.setAddress(address);
        return clientResponseMapper.entityToResponseModel(clientRepository.save(client));
    }

    @Override
    public ClientResponseModel updateClient(ClientRequestModel clientRequestModel, String clientId) {

        Client existingClient = clientRepository.findByClientIdentifier_ClientId(clientId);

        if (existingClient == null) {
            throw new NotFoundException("Unknown clientId: " + clientId);
        }

        Address address = new Address(clientRequestModel.getStreetAddress(), clientRequestModel.getCity(),
                clientRequestModel.getProvince(), clientRequestModel.getCountry(),
                clientRequestModel.getPostalCode());

        Client updatedClient = clientRequestMapper.requestModelToEntity(clientRequestModel,
                existingClient.getClientIdentifier(), address);

        updatedClient.setId(existingClient.getId());

        Client response = clientRepository.save(updatedClient);

        return clientResponseMapper.entityToResponseModel(response);
    }

    @Override
    public void deleteClient(String clientId) {

        Client existingClient = clientRepository.findByClientIdentifier_ClientId(clientId);

        if (existingClient == null) {
            throw new NotFoundException("Unknown clientId: " + clientId);
        }

        clientRepository.delete(existingClient);
    }

}
