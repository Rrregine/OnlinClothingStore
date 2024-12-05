package com.onlineclothingstore.apigateway.domainclientlayer.inventory.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.inventory.InventoryResponseModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.product.ProductRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.product.ProductResponseModel;
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
public class InventoryServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String INVENTORY_SERVICE_BASE_URL;

    private InventoryServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                                   @Value("${app.inventory-service.host}") String inventoryServiceHost,
                                   @Value("${app.inventory-service.port}") String inventoryServicePort){
        this.restTemplate = restTemplate;
        this.mapper = mapper;

        INVENTORY_SERVICE_BASE_URL = "http://" + inventoryServiceHost + ":" + inventoryServicePort + "/api/v1/inventories";
    }

    public List<InventoryResponseModel> getAllInventories(){
        try{

            String url = INVENTORY_SERVICE_BASE_URL;

            InventoryResponseModel[] inventoryResponseModels = restTemplate.getForObject(url, InventoryResponseModel[].class);

            return Arrays.asList(inventoryResponseModels);

        } catch (HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public InventoryResponseModel getInventoryByInventoryId(String inventoryId) {
        try {
            String url = INVENTORY_SERVICE_BASE_URL + "/" + inventoryId;

            InventoryResponseModel inventoryResponseModel = restTemplate.getForObject(url, InventoryResponseModel.class);

            return inventoryResponseModel;

        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel){
        try{
            String url = INVENTORY_SERVICE_BASE_URL;

            InventoryResponseModel inventoryResponseModel = restTemplate.postForObject(url, inventoryRequestModel, InventoryResponseModel.class);

            return inventoryResponseModel;
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public InventoryResponseModel updateInventoryByInventoryId(InventoryRequestModel inventoryRequestModel, String inventoryId) {
        try {
            String url = INVENTORY_SERVICE_BASE_URL + "/" + inventoryId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<InventoryRequestModel> requestEntity = new HttpEntity<>(inventoryRequestModel, headers);
            ResponseEntity<InventoryResponseModel> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, InventoryResponseModel.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Update failed with HTTP status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public void deleteInventoryByInventoryId(String inventoryId){
        try{
            String url = INVENTORY_SERVICE_BASE_URL + "/" + inventoryId;

            restTemplate.delete(url);
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public List<ProductResponseModel> getAllProductsByInventory(String inventoryId){
        try{

            String url = INVENTORY_SERVICE_BASE_URL + "/" + inventoryId + "/products";

            ProductResponseModel[] productResponseModels = restTemplate.getForObject(url, ProductResponseModel[].class);

            return Arrays.asList(productResponseModels);

        } catch (HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public ProductResponseModel getProductByInventoryIdAndProductId(String inventoryId, String productId) {
        try {
            String url = INVENTORY_SERVICE_BASE_URL + "/" + inventoryId + "/products/" + productId;

            ProductResponseModel productResponseModel = restTemplate.getForObject(url, ProductResponseModel.class);

            return productResponseModel;

        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public ProductResponseModel addProduct(ProductRequestModel productRequestModel, String inventoryId){
        try{
            String url = INVENTORY_SERVICE_BASE_URL + "/" + inventoryId + "/products";

            ProductResponseModel productResponseModel = restTemplate.postForObject(url, productRequestModel, ProductResponseModel.class);

            return productResponseModel;
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public ProductResponseModel updateProductByInventoryIdAndProductId(ProductRequestModel productRequestModel, String inventoryId, String productId) {
        try {
            String url = INVENTORY_SERVICE_BASE_URL + "/" + inventoryId + "/products/" + productId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<ProductRequestModel> requestEntity = new HttpEntity<>(productRequestModel, headers);
            ResponseEntity<ProductResponseModel> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ProductResponseModel.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Update failed with HTTP status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public void deleteProductByInventoryIdAndProductId(String inventoryId, String productId){
        try{
            String url = INVENTORY_SERVICE_BASE_URL + "/" + inventoryId + "/products/" + productId;

            restTemplate.delete(url);
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {

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
