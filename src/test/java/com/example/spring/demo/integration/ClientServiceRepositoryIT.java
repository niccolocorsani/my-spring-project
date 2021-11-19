package com.example.spring.demo.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.repositories.client.ClientRepository;
import com.example.spring.demo.services.client.ClientService;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(ClientService.class)
public class ClientServiceRepositoryIT {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRepository clientRepository;

	@Test
	public void testServiceCanInsertIntoRepository() {
		Client saved = clientService.insertNewClient(new Client(1L, "Marco", "Rossi"));
		assertThat(clientRepository.findById(saved.getId())).isPresent();
	}

	@Test
	public void testServiceCanUpdateRepository() {
		Client saved = clientRepository.save(new Client(1L, "Marco", "Rossi"));
		Client modified = clientService.updateClientById(saved.getId(), new Client(saved.getId(), "modified", ""));
		assertThat(clientRepository.findById(saved.getId())).contains(modified);
	}


	@Test

	public void testServiceDeleteClientByID() {
		Client saved = clientService.insertNewClient(new Client(1L, "Marco", "Rossi"));
		clientService.deleteClientById(1L);
		assertNull(clientService.getClientById(saved.getId()));
	}
}