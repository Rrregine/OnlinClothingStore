package com.onlineclothingstore.apigateway.domainclientlayer.employees.departments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineclothingstore.apigateway.presentationlayer.employees.departments.DepartmentRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.employees.departments.DepartmentResponseModel;
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
public class DepartmentsServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String DEPARTMENT_SERVICE_BASE_URL;

    private DepartmentsServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                                 @Value("${app.employees-service.host}") String employeeServiceHost,
                                 @Value("${app.employees-service.port}") String employeeServicePort){
        this.restTemplate = restTemplate;
        this.mapper = mapper;

        DEPARTMENT_SERVICE_BASE_URL = "http://" + employeeServiceHost + ":" + employeeServicePort + "/api/v1/departments";
    }

    public List<DepartmentResponseModel> getAllDepartments(){
        try{

            String url = DEPARTMENT_SERVICE_BASE_URL;

            DepartmentResponseModel[] departmentResponseModels = restTemplate.getForObject(url, DepartmentResponseModel[].class);

            return Arrays.asList(departmentResponseModels);

        } catch (HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public DepartmentResponseModel getDepartmentByDepartmentId(String departmentId) {
        try {
            String url = DEPARTMENT_SERVICE_BASE_URL + "/" + departmentId;

            DepartmentResponseModel departmentResponseModel = restTemplate.getForObject(url, DepartmentResponseModel.class);

            return departmentResponseModel;

        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public DepartmentResponseModel addDepartment(DepartmentRequestModel departmentRequestModel){
        try{
            String url = DEPARTMENT_SERVICE_BASE_URL;

            DepartmentResponseModel departmentResponseModel = restTemplate.postForObject(url, departmentRequestModel, DepartmentResponseModel.class);

            return departmentResponseModel;
        }
        catch(HttpClientErrorException ex){
            throw handleHttpClientException(ex);
        }
    }

    public DepartmentResponseModel updateDepartmentByDepartmentId(DepartmentRequestModel departmentRequestModel, String departmentId) {
        try {
            String url = DEPARTMENT_SERVICE_BASE_URL + "/" + departmentId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<DepartmentRequestModel> requestEntity = new HttpEntity<>(departmentRequestModel, headers);
            ResponseEntity<DepartmentResponseModel> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, DepartmentResponseModel.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Update failed with HTTP status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public void deleteDepartmentByDepartmentId(String departmentId){
        try{
            String url = DEPARTMENT_SERVICE_BASE_URL + "/" + departmentId;

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
