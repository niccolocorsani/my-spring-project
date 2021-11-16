package com.example.spring.demo.controllers.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.services.client.ClientService;


@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping("/api/clients")
	public List<Client> getAllClients() {
		return clientService.getAllClients();
	}

	
    @GetMapping(value = "/{idUser}")
    public Client getUser(@PathVariable("idUser") Long idClient) {
        Client client = this.clientService.getClientById(idClient);
        return client;
    }
    
    
    @PutMapping( value = "/putUser")
    public Client putUser(@RequestBody Client client) {
        this.clientService.insertNewClient(client);
        return client;
    }
    
    
    
    
    @PutMapping( value = "/updateUser")
    public Client updateUser(@RequestBody Client client) {
        this.clientService.updateClientById(client.getId(), client);
        return client;
    }
    
    
    
    @DeleteMapping("/{idClient}")
    public void deleteCallCenter(@PathVariable Long idClient) {
        this.clientService.deleteUserById(idClient);
    }
    
    
       
    
}
