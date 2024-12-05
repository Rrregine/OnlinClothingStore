package com.onlineclothingstore.sales.domainclientlayer.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineclothingstore.sales.utils.HttpErrorInfo;
import com.onlineclothingstore.sales.utils.exceptions.InvalidInputException;
import com.onlineclothingstore.sales.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
@Slf4j
public class ClientServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String CLIENTS_SERVICE_BASE_URL;

    public ClientServiceClient(RestTemplate restTemplate, ObjectMapper objectMapper,
                               @Value("${app.clients-service.host}") String clientServiceHost,
                               @Value("${app.clients-service.port}") String clientServicePort) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;

        CLIENTS_SERVICE_BASE_URL = "http://" + clientServiceHost + ":" + clientServicePort + "/api/v1/clients";
    }

    public ClientModel getClientByClientId(String clientId){
        try{

            String url = CLIENTS_SERVICE_BASE_URL + "/" + clientId;

            ClientModel clientModel = restTemplate.getForObject(url, ClientModel.class);

            return clientModel;

        } catch (HttpClientErrorException ex){
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
            return objectMapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException ioex) {
            return ioex.getMessage();
        }
    }
}
