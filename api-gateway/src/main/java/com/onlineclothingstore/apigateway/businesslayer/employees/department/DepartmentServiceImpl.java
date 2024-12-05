package com.onlineclothingstore.apigateway.businesslayer.employees.department;

import com.onlineclothingstore.apigateway.domainclientlayer.employees.departments.DepartmentsServiceClient;
import com.onlineclothingstore.apigateway.mappinglayer.employees.DepartmentResponseMapper;
import com.onlineclothingstore.apigateway.presentationlayer.employees.departments.DepartmentRequestModel;
import com.onlineclothingstore.apigateway.presentationlayer.employees.departments.DepartmentResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentsServiceClient departmentsServiceClient;
    private final DepartmentResponseMapper departmentResponseMapper;


    public DepartmentServiceImpl(DepartmentsServiceClient departmentsServiceClient,
                                 DepartmentResponseMapper departmentResponseMapper) {
        this.departmentsServiceClient = departmentsServiceClient;
        this.departmentResponseMapper = departmentResponseMapper;
    }

    @Override
    public List<DepartmentResponseModel> getAllDepartments() {
        return departmentResponseMapper.responseModelListToResponseModelList(departmentsServiceClient.getAllDepartments());
    }

    @Override
    public DepartmentResponseModel getDepartmentByDepartmentId(String departmentId) {
        return departmentResponseMapper.responseModelToResponseModel(departmentsServiceClient.getDepartmentByDepartmentId(departmentId));
    }

    @Override
    public DepartmentResponseModel addDepartment(DepartmentRequestModel departmentRequestModel) {
        return departmentResponseMapper.responseModelToResponseModel(departmentsServiceClient.addDepartment(departmentRequestModel));
    }

    @Override
    public DepartmentResponseModel updateDepartment(DepartmentRequestModel departmentRequestModel, String departmentId) {
        return departmentResponseMapper.responseModelToResponseModel(departmentsServiceClient.updateDepartmentByDepartmentId(departmentRequestModel, departmentId));
    }

    @Override
    public void deleteDepartment(String departmentId) {
        departmentsServiceClient.deleteDepartmentByDepartmentId(departmentId);
    }
}
