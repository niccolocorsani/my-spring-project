package com.example.spring.demo.services.client;

import java.util.List;

import com.example.spring.demo.model.appoitmnent.Appointment;
import org.springframework.stereotype.Service;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.repositories.client.ClientRepository;


@Service
public class ClientService {

	private ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	public Client getClientById(long id) {
		return clientRepository.findById(id).orElse(null);
	}

	public Client insertNewClient(Client client) {
		return clientRepository.save(client);
	}

	public Client updateClientById(long id, Client replacement) {
		replacement.setId(id);
		return clientRepository.save(replacement);
	}

	public void deleteClientById(Long idClient) {
		clientRepository.delete(clientRepository.getById(idClient));		
		
	}
}
