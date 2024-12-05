package com.onlineclothingstore.apigateway.domainclientlayer.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineclothingstore.apigateway.presentationlayer.clients.ClientRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.clients.ClientResponseModel;
import com.onlineclothingstore.apigateway.utils.HttpErrorInfo;
import com.onlineclothingstore.apigateway.utils.exceptions.InvalidInputException;
import com.onlineclothingstore.apigateway.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
@Slf4j
public class ClientsServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String CLIENT_SERVICE_BASE_URL;

    private ClientsServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                                 @Value("${app.clients-service.host}") String clientServiceHost,
                                 @Value("${app.clients-service.port}") String clientServicePort){
        this.restTemplate = restTemplate;
        this.mapper = mapper;

        CLIENT_SERVICE_BASE_URL = "http://" + clientServiceHost + ":" + clientServicePort + "/api/v1/clients";
    }

    public List<ClientResponseModel> getAllClients(){
        try{

            String url = CLIENT_SERVICE_BASE_URL;

            ClientResponseModel[] clientResponseModels = restTemplate.getForObject(url, ClientResponseModel[].class);

            return Arrays.asList(clientResponseModels);

        } catch (HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public ClientResponseModel getClientByClientId(String clientId) {
        try {
            String url = CLIENT_SERVICE_BASE_URL + "/" + clientId;

            ClientResponseModel clientResponseModel = restTemplate.getForObject(url, ClientResponseModel.class);

            return clientResponseModel;

        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public ClientResponseModel addClient(ClientRequestModel clientRequestModel){
        try{
            String url = CLIENT_SERVICE_BASE_URL;

            ClientResponseModel clientResponseModel = restTemplate.postForObject(url, clientRequestModel, ClientResponseModel.class);

            return clientResponseModel;
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public ClientResponseModel updateClientByClientId(ClientRequestModel clientRequestModel, String clientId) {
        try {
            String url = CLIENT_SERVICE_BASE_URL + "/" + clientId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<ClientRequestModel> requestEntity = new HttpEntity<>(clientRequestModel, headers);
            ResponseEntity<ClientResponseModel> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ClientResponseModel.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Update failed with HTTP status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public void deleteClientByClientId(String clientId){
        try{
            String url = CLIENT_SERVICE_BASE_URL + "/" + clientId;

            restTemplate.delete(url);
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
//include all possible responses from the client
        if (ex.getStatusCode() == NOT_FOUND) {
            return new NotFoundException(getErrorMessage(ex));
        }
        if (ex.getStatusCode() == UNPROCESSABLE_ENTITY) {
            return new InvalidInputException(getErrorMessage(ex));
        }
        log.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
        log.warn("Error body: {}", ex.getResponseBodyAsString());
        return ex;
    }


    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException ioex) {
            return ioex.getMessage();
        }
    }
}
