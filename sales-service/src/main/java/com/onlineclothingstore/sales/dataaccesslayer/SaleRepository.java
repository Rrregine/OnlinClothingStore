package com.onlineclothingstore.sales.dataaccesslayer;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SaleRepository extends MongoRepository<Sale, String> {

    List<Sale> findSalesByClientModel_ClientId(String clientId);

    Sale findSaleByClientModel_ClientIdAndSaleIdentifier_SaleId(String clientId, String saleId);

    void deleteSaleBySaleIdentifier_SaleId(String saleId);

    boolean existsSaleBySaleIdentifier_SaleId(String saleId);

}
