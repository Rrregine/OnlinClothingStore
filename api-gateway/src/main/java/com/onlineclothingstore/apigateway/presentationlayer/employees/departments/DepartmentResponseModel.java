package com.onlineclothingstore.apigateway.presentationlayer.employees.departments;


import com.onlineclothingstore.apigateway.domainclientlayer.employees.departments.Position;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DepartmentResponseModel  extends RepresentationModel<DepartmentResponseModel> {
    private String departmentId;
    private String name;
    private Integer headCount;
    private List<Position> positions;

}
