package com.onlineclothingstore.apigateway.presentationlayer.clients;

import com.onlineclothingstore.apigateway.domainclientlayer.clients.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientRequestModel {

    String username;
    String emailAddress;
    String streetAddress;
    String city;
    String province;
    String country;
    String postalCode;
    List<PhoneNumber> phoneNumbers;
}
