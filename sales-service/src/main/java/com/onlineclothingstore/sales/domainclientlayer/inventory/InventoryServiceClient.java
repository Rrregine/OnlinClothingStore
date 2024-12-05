package com.onlineclothingstore.sales.domainclientlayer.inventory;

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
public class InventoryServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String PRODUCTS_SERVICE_BASE_URL;

    public InventoryServiceClient(RestTemplate restTemplate, ObjectMapper objectMapper,
                               @Value("${app.inventory-service.host}") String inventoryServiceHost,
                               @Value("${app.inventory-service.port}") String inventoryServicePort) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;

        PRODUCTS_SERVICE_BASE_URL = "http://" + inventoryServiceHost + ":" + inventoryServicePort + "/api/v1/inventories";
    }

    public ProductModel getProductByProductIdAndInventoryId(String productId, String inventoryId){
        try{

            String url = PRODUCTS_SERVICE_BASE_URL + "/" + inventoryId + "/products/" + productId;

            ProductModel productModel = restTemplate.getForObject(url, ProductModel.class);

            return productModel;

        } catch (HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public InventoryModel getInventoryByInventoryId(String inventoryId){
        try{

            String url = PRODUCTS_SERVICE_BASE_URL + "/" + inventoryId;

            InventoryModel inventoryModel = restTemplate.getForObject(url, InventoryModel.class);

            return inventoryModel;

        } catch (HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public void patchProductByInventoryId_ProductId(String productId, String inventoryId, String newStatus){
        try{

            String url = PRODUCTS_SERVICE_BASE_URL + "/" + inventoryId + "/products/" + productId + "/" + newStatus;

            restTemplate.patchForObject(url, newStatus, ProductModel.class);

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
