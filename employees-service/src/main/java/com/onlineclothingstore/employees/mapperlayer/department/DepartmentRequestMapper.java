package com.onlineclothingstore.employees.mapperlayer.department;

import com.onlineclothingstore.employees.dataaccesslayer.department.Department;
import com.onlineclothingstore.employees.dataaccesslayer.department.DepartmentIdentifier;
import com.onlineclothingstore.employees.presentationlayer.department.DepartmentRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentRequestMapper {

    @Mapping(target = "id", ignore = true)
    Department requestModelToEntity(DepartmentRequestModel departmentRequestDTO,
                                    DepartmentIdentifier departmentIdentifier);
}
