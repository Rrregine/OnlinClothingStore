package com.onlineclothingstore.sales.domainclientlayer.clients;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class ClientModel {

    String clientId;
    String username;
}
