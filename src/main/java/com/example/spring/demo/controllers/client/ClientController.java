package com.example.spring.demo.controllers.client;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping("/api/clients")
	public List<Client> getAllClients() {

        return clientService.getAllClients();
	}

	
    @GetMapping(value = "/{idClient}")
    public Client getClient(@PathVariable("idClient")  Long idClient) {
        return  this.clientService.getClientById(idClient);
    }

    @PutMapping( value = "/putClient")
    public Client putClient(@RequestBody ClientDTO client) {
	    Client c = new Client();
	    c.setId(client.getId());
        this.clientService.insertNewClient(c);
        return c;
    }


    @PutMapping( value = "/updateClient")
    public Client updateClient(@RequestBody Client client) {

        this.clientService.updateClientById(client);
        return client;
    }
    
    
    @DeleteMapping("/{idClient}")
    public void deleteClientCenter(@PathVariable Long idClient) {
        this.clientService.deleteClientById(idClient);
    }




    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleNullPointerException(Exception e)
    {
        String error = "";
        error = e.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(error, status);
    }

}
