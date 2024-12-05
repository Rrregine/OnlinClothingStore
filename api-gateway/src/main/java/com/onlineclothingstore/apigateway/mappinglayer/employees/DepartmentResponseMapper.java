package com.onlineclothingstore.apigateway.mappinglayer.employees;

import com.onlineclothingstore.apigateway.presentationlayer.employees.departments.DepartmentController;
import com.onlineclothingstore.apigateway.presentationlayer.employees.departments.DepartmentResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DepartmentResponseMapper {

    DepartmentResponseModel responseModelToResponseModel(DepartmentResponseModel departmentResponseModel);

    List<DepartmentResponseModel> responseModelListToResponseModelList(List<DepartmentResponseModel> departmentResponseModels);

    @AfterMapping
    default void addLinks(@MappingTarget DepartmentResponseModel departmentResponseModel){

        Link allLink = linkTo(methodOn(DepartmentController.class)
                .getAllDepartments())
                .withSelfRel();
        departmentResponseModel.add(allLink);

        Link selfLink = linkTo(methodOn(DepartmentController.class)
                .getDepartmentByDepartmentId(departmentResponseModel.getDepartmentId()))
                .withSelfRel();
        departmentResponseModel.add(selfLink);
    }

}
