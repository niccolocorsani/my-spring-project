package com.example.spring.demo.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.repositories.client.ClientRepository;
import com.example.spring.demo.services.client.ClientService;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(ClientService.class)
 class ClientServiceRepositoryIT {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRepository clientRepository;

	@Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

	 void testServiceCanInsertIntoRepository() {
		Client saved = clientService.insertNewClient(new Client(1L, "Marco", "Rossi"));
		assertThat(clientRepository.findById(saved.getId())).isPresent();
	}

	@Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

	 void testServiceCanUpdateRepository() {
		Client saved = clientRepository.save(new Client(1L, "Marco", "Rossi"));
		Client modified = clientService.updateClientById(saved.getId(), new Client(saved.getId(), "modified", ""));
		assertThat(clientRepository.findById(saved.getId())).contains(modified);
	}


	@Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

	 void testServiceDeleteClientByID() {
		Client saved = clientService.insertNewClient(new Client(1L, "Marco", "Rossi"));
        clientService.insertNewClient(saved);
		clientService.deleteClientById(1L);
        assertNull(clientService.getClientById(saved.getId()));
		
	}
	
	
	@Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

	void testServiceGetClientByID() {
		Client client = new Client();
		client.setId(1L);
		clientService.insertNewClient(client);

		clientService.getClientById(1L);
		assertEquals(clientService.getClientById(client.getId()).getId(),1L);

	}
	
	
	
	@Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
	 void testServiceGetAllClients() {
		Client client = new Client();
		client.setId(1L);
		clientService.insertNewClient(client);
		assertTrue(clientService.getAllClients().size()==1);
		
	}
	
}