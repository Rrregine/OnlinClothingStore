package com.onlineclothingstore.employees.mapperlayer.department;

import com.onlineclothingstore.employees.dataaccesslayer.department.Department;
import com.onlineclothingstore.employees.presentationlayer.department.DepartmentResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentResponseMapper {

    @Mapping(expression = "java(department.getDepartmentIdentifier().getDepartmentId())", target = "departmentId")
    DepartmentResponseModel entityToResponseModel(Department department);

    List<DepartmentResponseModel> entityListToResponseModelList(List<Department> departmentList);
}
