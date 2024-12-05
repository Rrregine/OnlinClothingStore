package com.onlineclothingstore.clients.presentationlayer;

import com.onlineclothingstore.clients.dataaccesslayer.PhoneNumber;
import lombok.*;

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
