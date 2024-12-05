package com.onlineclothingstore.clients.presentationlayer;

import com.onlineclothingstore.clients.businesslayer.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientResponseModel>> getClients() {
        return ResponseEntity.ok().body(clientService.getClients());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseModel> getClientByClientId(@PathVariable String clientId) {
        return ResponseEntity.ok().body(clientService.getClientByClientId(clientId));
    }

    @PostMapping()
    public ResponseEntity<ClientResponseModel> addClient(@RequestBody ClientRequestModel clientRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.addClient(clientRequestModel));
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientResponseModel> updateClient(@RequestBody ClientRequestModel customerRequestModel, @PathVariable String clientId) {
        return ResponseEntity.ok().body(clientService.updateClient(customerRequestModel,clientId));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable String clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
