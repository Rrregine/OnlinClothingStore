package com.onlineclothingstore.apigateway.domainclientlayer.employees.employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineclothingstore.apigateway.presentationlayer.employees.employees.EmployeeRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.employees.employees.EmployeeResponseModel;
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
public class EmployeesServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String EMPLOYEE_SERVICE_BASE_URL;

    private EmployeesServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                                     @Value("${app.employees-service.host}") String employeeServiceHost,
                                     @Value("${app.employees-service.port}") String employeeServicePort){
        this.restTemplate = restTemplate;
        this.mapper = mapper;

        EMPLOYEE_SERVICE_BASE_URL = "http://" + employeeServiceHost + ":" + employeeServicePort + "/api/v1/employees";
    }

    public List<EmployeeResponseModel> getAllEmployees(){
        try{

            String url = EMPLOYEE_SERVICE_BASE_URL;

            EmployeeResponseModel[] employeeResponseModels = restTemplate.getForObject(url, EmployeeResponseModel[].class);

            return Arrays.asList(employeeResponseModels);

        } catch (HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public EmployeeResponseModel getEmployeeByEmployeeId(String employeeId) {
        try {
            String url = EMPLOYEE_SERVICE_BASE_URL + "/" + employeeId;

            EmployeeResponseModel employeeResponseModel = restTemplate.getForObject(url, EmployeeResponseModel.class);

            return employeeResponseModel;

        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public EmployeeResponseModel addEmployee(EmployeeRequestModel employeeRequestModel){
        try{
            String url = EMPLOYEE_SERVICE_BASE_URL;

            EmployeeResponseModel employeeResponseModel = restTemplate.postForObject(url, employeeRequestModel, EmployeeResponseModel.class);

            return employeeResponseModel;
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public EmployeeResponseModel updateEmployeeByEmployeeId(EmployeeRequestModel employeeRequestModel, String employeeId) {
        try {
            String url = EMPLOYEE_SERVICE_BASE_URL + "/" + employeeId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<EmployeeRequestModel> requestEntity = new HttpEntity<>(employeeRequestModel, headers);
            ResponseEntity<EmployeeResponseModel> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, EmployeeResponseModel.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Update failed with HTTP status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public void deleteEmployeeByEmployeeId(String employeeId){
        try{
            String url = EMPLOYEE_SERVICE_BASE_URL + "/" + employeeId;

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
