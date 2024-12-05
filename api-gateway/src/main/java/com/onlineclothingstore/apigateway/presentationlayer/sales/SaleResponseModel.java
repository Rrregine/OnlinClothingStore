package com.onlineclothingstore.apigateway.presentationlayer.sales;

import com.onlineclothingstore.apigateway.domainclientlayer.sales.SalesStatus;
import com.onlineclothingstore.apigateway.presentationlayer.inventory.product.ProductResponseModel;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseModel extends RepresentationModel<SaleResponseModel> {

    String saleId;
    String inventoryId;
    String productId;

    String productSize;
    String productModel;
    String productImage;
//    String productBrand;
//    String productCountry;

    String clientId;
    String clientUsername;

    String employeeId;
    String employeeFirstName;
    String employeeLastName;

    SalesStatus salesStatus;

}
