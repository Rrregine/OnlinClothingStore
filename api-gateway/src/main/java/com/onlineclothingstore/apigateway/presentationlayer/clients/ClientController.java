package com.onlineclothingstore.apigateway.presentationlayer.clients;

import com.onlineclothingstore.apigateway.businesslayer.clients.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
@Slf4j
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(
            value="",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<List<ClientResponseModel>> getAllClients(){
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    @GetMapping(
            value="/{clientId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<ClientResponseModel> getClientByClientId(@PathVariable String clientId){
        return ResponseEntity.ok().body(clientService.getClientByClientId(clientId));
    }

    @PostMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClientResponseModel> addClient(@RequestBody ClientRequestModel clientRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.addClient(clientRequestModel));
    }

    @PutMapping(
            value = "/{clientId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClientResponseModel> updateClient(@RequestBody ClientRequestModel customerRequestModel, @PathVariable String clientId) {
        return ResponseEntity.ok().body(clientService.updateClient(customerRequestModel,clientId));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable String clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
