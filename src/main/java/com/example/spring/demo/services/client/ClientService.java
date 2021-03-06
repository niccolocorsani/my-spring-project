package com.example.spring.demo.services.client;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.repositories.client.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;


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

	public Client updateClientById(Client replacement) {
		return clientRepository.save(replacement);
	}

	public Client deleteClientById(Long idClient) {
		clientRepository.delete(clientRepository.getById(idClient));
		return null;		
		
	}

}
