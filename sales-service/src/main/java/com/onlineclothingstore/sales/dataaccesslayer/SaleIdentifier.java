package com.onlineclothingstore.sales.dataaccesslayer;

import lombok.Getter;

import java.util.UUID;

@Getter
public class SaleIdentifier {

    String saleId;

    public SaleIdentifier(){
        this.saleId = UUID.randomUUID().toString();
    }

    public SaleIdentifier(String saleId) {
        this.saleId = saleId;
    }
}
