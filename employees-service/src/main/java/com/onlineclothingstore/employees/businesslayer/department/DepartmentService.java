package com.onlineclothingstore.employees.businesslayer.department;

import com.onlineclothingstore.employees.presentationlayer.department.DepartmentRequestModel;
import com.onlineclothingstore.employees.presentationlayer.department.DepartmentResponseModel;
import com.onlineclothingstore.employees.presentationlayer.employee.EmployeeResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    List<DepartmentResponseModel> getAllDepartments();

  //  List<EmployeeResponseModel> getAllEmployeesByDepartmentId(String departmentId);

    DepartmentResponseModel getDepartmentByDepartmentId(String departmentId);

    DepartmentResponseModel addDepartment(DepartmentRequestModel departmentRequestModel);

    DepartmentResponseModel updateDepartment(DepartmentRequestModel departmentRequestModel, String departmentId);

    void deleteDepartment(String departmentId);
}
