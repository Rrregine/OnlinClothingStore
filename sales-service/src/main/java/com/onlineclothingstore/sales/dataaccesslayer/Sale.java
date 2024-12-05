package com.onlineclothingstore.sales.dataaccesslayer;

import com.onlineclothingstore.sales.domainclientlayer.clients.ClientModel;
import com.onlineclothingstore.sales.domainclientlayer.employees.EmployeeModel;
import com.onlineclothingstore.sales.domainclientlayer.inventory.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(collection = "sales")
public class Sale {

    @Id
    private String id;

    @Indexed(unique = true)
    SaleIdentifier saleIdentifier;
    private ClientModel clientModel;
    private EmployeeModel employeeModel;
    private ProductModel productModel;
    private SalesStatus salesStatus;

}
