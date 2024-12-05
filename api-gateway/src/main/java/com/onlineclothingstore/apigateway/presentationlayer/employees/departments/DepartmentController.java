package com.onlineclothingstore.apigateway.presentationlayer.employees.departments;

import com.onlineclothingstore.apigateway.businesslayer.employees.department.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentResponseModel>> getAllDepartments(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getAllDepartments());
    }

    @GetMapping("{departmentId}")
    public ResponseEntity<DepartmentResponseModel> getDepartmentByDepartmentId(@PathVariable String departmentId){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getDepartmentByDepartmentId(departmentId));
    }


//    @GetMapping("{departmentId}/employees")
//    public ResponseEntity<List<EmployeeResponseModel>> getEmployeesByDepartment(@PathVariable String departmentId){
//        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getAllEmployeesByDepartmentId(departmentId));
//    }

    @PostMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DepartmentResponseModel> addDepartment(@RequestBody DepartmentRequestModel departmentRequestModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addDepartment(departmentRequestModel));
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponseModel> updateDepartment(@RequestBody DepartmentRequestModel departmentRequestModel,
                                                                  @PathVariable String departmentId){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.updateDepartment(departmentRequestModel,departmentId));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
