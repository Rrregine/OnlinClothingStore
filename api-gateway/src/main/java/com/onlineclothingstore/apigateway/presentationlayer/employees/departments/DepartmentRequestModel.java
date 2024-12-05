package com.onlineclothingstore.apigateway.presentationlayer.employees.departments;

import com.onlineclothingstore.apigateway.domainclientlayer.employees.departments.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestModel {

    private String name;
    private Integer headCount;
    private List<Position> positions;
}
