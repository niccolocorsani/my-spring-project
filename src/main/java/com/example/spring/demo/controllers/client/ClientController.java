package com.example.spring.demo.controllers.client;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping("/api/clients")
	public List<Client> getAllClients() {
		return clientService.getAllClients();
	}

	
    @GetMapping(value = "/{idClient}")
    public Client getClient(@PathVariable("idClient") Long idClient) {
        Client client = this.clientService.getClientById(idClient);
        return client;
    }
    
    
    @PutMapping( value = "/putClient")
    public Client putClient(@RequestBody Client client) {
        this.clientService.insertNewClient(client);
        return client;
    }
    

    @PutMapping( value = "/updateClient")
    public Client updateClient(@RequestBody Client client) {
        this.clientService.updateClientById(client.getId(), client);
        return client;
    }
    
    
    @DeleteMapping("/{idClient}")
    public void deleteClientCenter(@PathVariable Long idClient) {
        this.clientService.deleteClientById(idClient);
    }
    
    
       
    
}
