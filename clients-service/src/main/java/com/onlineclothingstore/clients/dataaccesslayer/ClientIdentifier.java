package com.onlineclothingstore.clients.dataaccesslayer;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class ClientIdentifier {

    private String clientId;

    public ClientIdentifier() {
        this.clientId = UUID.randomUUID().toString();
    }

   /* public ClientIdentifier(String clientId){
        this.clientId = clientId;
    }*/
}
