package com.onlineclothingstore.apigateway.presentationlayer.employees.employees;

import com.onlineclothingstore.apigateway.businesslayer.employees.employee.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeResponseModel>> getAllEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<EmployeeResponseModel> getEmployeeByEmployeeId(@PathVariable String employeeId){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeByEmployeeId(employeeId));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseModel> addEmployee(@RequestBody EmployeeRequestModel employeeRequestModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employeeRequestModel));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseModel> updateEmployee(@RequestBody EmployeeRequestModel employeeRequestModel,
                                                              @PathVariable String employeeId){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employeeRequestModel,employeeId));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
