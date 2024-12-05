package com.onlineclothingstore.apigateway.domainclientlayer.sales;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryResponseModel;
import com.onlineclothingstore.apigateway.presentationlayer.sales.SaleRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.sales.SaleResponseModel;
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
public class SalesServiceClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String SALE_SERVICE_BASE_URL;

    private SalesServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                               @Value("${app.sales-service.host}") String salesServiceHost,
                               @Value("${app.sales-service.port}") String salesServicePort) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;

        SALE_SERVICE_BASE_URL = "http://" + salesServiceHost + ":" + salesServicePort + "/api/v1/clients";
    }

    public List<SaleResponseModel> getAllClientOrdersByClient(String clientId) {
        try {

            String url = SALE_SERVICE_BASE_URL + "/" + clientId + "/orders";

            SaleResponseModel[] saleResponseModels = restTemplate.getForObject(url, SaleResponseModel[].class);

            return Arrays.asList(saleResponseModels);

        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public SaleResponseModel getClientOrderBySaleId(String clientId, String saleId) {
        try {
            String url = SALE_SERVICE_BASE_URL + "/" + clientId + "/orders/" + saleId;

            SaleResponseModel saleResponseModel = restTemplate.getForObject(url, SaleResponseModel.class);

            return saleResponseModel;

        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public SaleResponseModel addClientOrder(SaleRequestModel saleRequestModel, String clientId) {
        try {
            String url = SALE_SERVICE_BASE_URL + "/" + clientId + "/orders/";

            SaleResponseModel saleResponseModel = restTemplate.postForObject(url, saleRequestModel, SaleResponseModel.class);

            return saleResponseModel;
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public SaleResponseModel updateClientOrder (SaleRequestModel saleRequestModel, String clientId, String saleId) {
        try {
            String url = SALE_SERVICE_BASE_URL + "/" + clientId + "/orders/" + saleId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<SaleRequestModel> requestEntity = new HttpEntity<>(saleRequestModel, headers);
            ResponseEntity<SaleResponseModel> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, SaleResponseModel.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Update failed with HTTP status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public void deleteClientOrderFromClient(String clientId, String saleId) {
        try {
            String url = SALE_SERVICE_BASE_URL + "/" + clientId + "/orders/" + saleId;

            restTemplate.delete(url);
        } catch (HttpClientErrorException ex) {
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
        } catch (IOException ioex) {
            return ioex.getMessage();
        }
    }
}
