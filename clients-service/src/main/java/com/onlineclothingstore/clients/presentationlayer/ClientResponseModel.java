package com.onlineclothingstore.clients.presentationlayer;

import com.onlineclothingstore.clients.dataaccesslayer.PhoneNumber;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientResponseModel extends RepresentationModel<ClientResponseModel> {

    String clientId;
    String username;
    String emailAddress;
    String streetAddress;
    String city;
    String province;
    String country;
    String postalCode;
    List<PhoneNumber> phoneNumbers;
}
