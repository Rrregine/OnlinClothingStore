package com.onlineclothingstore.sales.utils;

import com.onlineclothingstore.sales.dataaccesslayer.Sale;
import com.onlineclothingstore.sales.dataaccesslayer.SaleIdentifier;
import com.onlineclothingstore.sales.dataaccesslayer.SaleRepository;
import com.onlineclothingstore.sales.dataaccesslayer.SalesStatus;
import com.onlineclothingstore.sales.domainclientlayer.clients.ClientModel;
import com.onlineclothingstore.sales.domainclientlayer.employees.EmployeeModel;
import com.onlineclothingstore.sales.domainclientlayer.inventory.ProductModel;
import com.onlineclothingstore.sales.domainclientlayer.inventory.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DatabaseLoaderService implements CommandLineRunner {

    @Autowired
    SaleRepository saleRepository;

    @Override
    public void run(String... args) throws Exception {

        if(saleRepository.existsSaleBySaleIdentifier_SaleId("product10"))
            saleRepository.deleteSaleBySaleIdentifier_SaleId("product10");

        var saleIdentifier1 = new SaleIdentifier("product10");

        var productModel = ProductModel.builder()
                .productId("product10")
                .model("unisex")
                .size("S")
                .image("imageURL10")
                .status(Status.SALE_PENDING)
                .inventoryId("8e7a1530-c9ae-4674-8e85-199457cc5c62")
                .build();

        var clientModel = ClientModel.builder()
                .clientId("9a7f32d6-4286-4c63-af92-d57542607c3e")
                .username("DavidHernandez")
                .build();

        var employeeModel = EmployeeModel.builder()
                .employeeId("e2f9d89e-4d7f-4a52-a1a9-3fd3f9806f65")
                .firstName("Christopher")
                .lastName("Martinez")
                .build();

        var sale1 = Sale.builder()
                .saleIdentifier(saleIdentifier1)
                .clientModel(clientModel)
                .employeeModel(employeeModel)
                .productModel(productModel)
                .salesStatus(SalesStatus.ORDER_OFFER)
                .build();

        saleRepository.save(sale1);
    }
}
