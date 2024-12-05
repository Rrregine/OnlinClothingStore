package com.onlineclothingstore.employees.businesslayer.department;

import com.onlineclothingstore.employees.dataaccesslayer.department.Department;
import com.onlineclothingstore.employees.dataaccesslayer.department.DepartmentIdentifier;
import com.onlineclothingstore.employees.dataaccesslayer.department.DepartmentRepository;
import com.onlineclothingstore.employees.dataaccesslayer.employee.Employee;
import com.onlineclothingstore.employees.dataaccesslayer.employee.EmployeeRepository;
import com.onlineclothingstore.employees.mapperlayer.department.DepartmentRequestMapper;
import com.onlineclothingstore.employees.mapperlayer.department.DepartmentResponseMapper;
import com.onlineclothingstore.employees.mapperlayer.employee.EmployeeResponseMapper;
import com.onlineclothingstore.employees.presentationlayer.department.DepartmentRequestModel;
import com.onlineclothingstore.employees.presentationlayer.department.DepartmentResponseModel;
import com.onlineclothingstore.employees.presentationlayer.employee.EmployeeResponseModel;
import com.onlineclothingstore.employees.utils.exceptions.IsEmptyException;
import com.onlineclothingstore.employees.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final DepartmentResponseMapper departmentResponseMapper;
    private final DepartmentRequestMapper departmentRequestMapper;
    //private final EmployeeRepository employeeRepository;
   // private final EmployeeResponseMapper employeeResponseMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentResponseMapper departmentResponseMapper, DepartmentRequestMapper departmentRequestMapper/*, EmployeeRepository employeeRepository, EmployeeResponseMapper employeeResponseMapper*/) {
        this.departmentRepository = departmentRepository;
        this.departmentResponseMapper = departmentResponseMapper;
        this.departmentRequestMapper = departmentRequestMapper;
        //this.employeeRepository = employeeRepository;
        //this.employeeResponseMapper = employeeResponseMapper;
    }


    @Override
    public List<DepartmentResponseModel> getAllDepartments() {

        List<Department> departmentEntities = departmentRepository.findAll();
        return departmentResponseMapper.entityListToResponseModelList(departmentEntities);
    }


  /*  @Override
    public List<EmployeeResponseModel> getAllEmployeesByDepartmentId(String departmentId) {

        List<Employee> employeeEntities = employeeRepository.findAllByDepartmentIdentifier_DepartmentId(departmentId);

        return employeeResponseMapper.entityListToResponseModelList(employeeEntities);
    } */

    @Override
    public DepartmentResponseModel getDepartmentByDepartmentId(String departmentId) {

        Department foundDepartment = departmentRepository.findByDepartmentIdentifier_DepartmentId(departmentId);

        if( foundDepartment == null){
            throw new NotFoundException("Unknown departmentId: " + departmentId);
        }

        return departmentResponseMapper.entityToResponseModel(foundDepartment);
    }

    @Override
    public DepartmentResponseModel addDepartment(DepartmentRequestModel departmentRequestModel) {

        if (departmentRequestModel.getName() == null) {
            throw new IsEmptyException("Name cannot be empty! ");
        }

        Department department = departmentRequestMapper.requestModelToEntity(departmentRequestModel, new DepartmentIdentifier());

        Department savedDepartment = departmentRepository.save(department);

        return departmentResponseMapper.entityToResponseModel(savedDepartment);
    }

    @Override
    public DepartmentResponseModel updateDepartment(DepartmentRequestModel departmentRequestModel, String departmentId) {

        Department foundDepartment = departmentRepository.findByDepartmentIdentifier_DepartmentId(departmentId);

        if (foundDepartment == null){
            throw new NotFoundException("Unknown departmentId : " + departmentId);
        }

        if (departmentRequestModel.getName() == null) {
            throw new IsEmptyException("Name cannot be empty! ");
        }

        Department department = departmentRequestMapper.requestModelToEntity(departmentRequestModel, foundDepartment.getDepartmentIdentifier());
        department.setId(foundDepartment.getId());
        Department savedDepartment = departmentRepository.save(department);

        return departmentResponseMapper.entityToResponseModel(savedDepartment);
    }

    @Override
    public void deleteDepartment(String departmentId) {

        Department foundDepartment = departmentRepository.findByDepartmentIdentifier_DepartmentId(departmentId);

        if (foundDepartment == null){
            throw new NotFoundException("Unknown departmentId : " + departmentId);
        }

        departmentRepository.delete(foundDepartment);
    }

}
