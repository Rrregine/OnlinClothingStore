package com.onlineclothingstore.apigateway.mappinglayer.employees;

import com.onlineclothingstore.apigateway.presentationlayer.employees.employees.EmployeeController;
import com.onlineclothingstore.apigateway.presentationlayer.employees.employees.EmployeeResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EmployeeResponseMapper {

    EmployeeResponseModel responseModelToResponseModel(EmployeeResponseModel employeeResponseModel);

    List<EmployeeResponseModel> responseModelListToResponseModelList(List<EmployeeResponseModel> employeeList);

    @AfterMapping
    default void addLinks(@MappingTarget EmployeeResponseModel employeeResponseModel){

        Link allLink = linkTo(methodOn(EmployeeController.class)
                .getAllEmployees())
                .withSelfRel();
        employeeResponseModel.add(allLink);

        Link selfLink = linkTo(methodOn(EmployeeController.class)
                .getEmployeeByEmployeeId(employeeResponseModel.getEmployeeId()))
                .withSelfRel();
        employeeResponseModel.add(selfLink);
    }
}
