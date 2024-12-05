package com.onlineclothingstore.sales.domainclientlayer.employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineclothingstore.sales.domainclientlayer.clients.ClientModel;
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
public class EmployeeServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String EMPLOYEES_SERVICE_BASE_URL;

    public EmployeeServiceClient(RestTemplate restTemplate, ObjectMapper objectMapper,
                               @Value("${app.employees-service.host}") String employeeServiceHost,
                               @Value("${app.employees-service.port}") String employeeServicePort) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;

        EMPLOYEES_SERVICE_BASE_URL = "http://" + employeeServiceHost + ":" + employeeServicePort + "/api/v1/employees";
    }

    public EmployeeModel getEmployeeByEmployeeId(String employeeId){
        try{

            String url = EMPLOYEES_SERVICE_BASE_URL + "/" + employeeId;

            EmployeeModel employeeModel = restTemplate.getForObject(url, EmployeeModel.class);

            return employeeModel;

        } catch (HttpClientErrorException ex){
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
            return objectMapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException ioex) {
            return ioex.getMessage();
        }
    }
}
