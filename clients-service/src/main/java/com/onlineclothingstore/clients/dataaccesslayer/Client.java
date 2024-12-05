package com.onlineclothingstore.clients.dataaccesslayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="clients")
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private ClientIdentifier clientIdentifier;

    private String username;
    private String emailAddress;

    @Embedded
    private Address address;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "client_phonenumbers", joinColumns = @JoinColumn(name="client_id"))
    private List<PhoneNumber> phoneNumbers;

  /*  public Client(@NotNull String username, @NotNull String emailAddress, @NotNull Address address,
                  @NotNull List<PhoneNumber> phoneNumberList) {
        this.clientIdentifier = new ClientIdentifier();
        this.username = username;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumbers = phoneNumberList;
    }*/
}
