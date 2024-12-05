package com.onlineclothingstore.employees.presentationlayer.department;

import com.onlineclothingstore.employees.dataaccesslayer.department.Position;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentResponseModel {
    private String departmentId;
    private String name;
    private Integer headCount;
    private List<Position> positions;

}
