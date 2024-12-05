package com.onlineclothingstore.apigateway.businesslayer.employees.department;

import com.onlineclothingstore.apigateway.presentationlayer.employees.departments.DepartmentRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.employees.departments.DepartmentResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    List<DepartmentResponseModel> getAllDepartments();

    DepartmentResponseModel getDepartmentByDepartmentId(String departmentId);

    DepartmentResponseModel addDepartment(DepartmentRequestModel departmentRequestModel);

    DepartmentResponseModel updateDepartment(DepartmentRequestModel departmentRequestModel, String departmentId);

    void deleteDepartment(String departmentId);
}
