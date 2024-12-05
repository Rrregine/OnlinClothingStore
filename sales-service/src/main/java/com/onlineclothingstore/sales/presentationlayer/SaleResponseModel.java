package com.onlineclothingstore.sales.presentationlayer;

import com.onlineclothingstore.sales.dataaccesslayer.SalesStatus;
import com.onlineclothingstore.sales.domainclientlayer.inventory.Options;
import com.onlineclothingstore.sales.domainclientlayer.inventory.Status;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseModel{

    private String saleId;
    private String inventoryId;
    private String productId;

    private String productSize;
    private String productModel;
    private String productImage;
    private Status productStatus;
//    private String productBrand;
//    private String productCountry;
    private List<Options> productOptionList;

    private String clientId;
    private String clientUsername;

    private String employeeId;
    private String employeeFirstName;
    private String employeeLastName;

    private SalesStatus salesStatus;

}
