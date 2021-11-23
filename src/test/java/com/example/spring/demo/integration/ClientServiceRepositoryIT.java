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
	 void testServiceCanInsertIntoRepository() {
		Client saved = clientService.insertNewClient(new Client(1L, "Marco", "Rossi"));
		assertThat(clientRepository.findById(saved.getId())).isPresent();
	}

	@Test
	 void testServiceCanUpdateRepository() {
		Client saved = clientRepository.save(new Client(1L, "Marco", "Rossi"));
		Client modified = clientService.updateClientById(saved.getId(), new Client(saved.getId(), "modified", ""));
		assertThat(clientRepository.findById(saved.getId())).contains(modified);
	}


	@Test
	 void testServiceDeleteClientByID() {
		Client saved = clientService.insertNewClient(new Client(1L, "Marco", "Rossi"));
		clientService.deleteClientById(1L);
		assertNotEquals(clientService.getClientById(saved.getId()).getId(),1L);
		//// TODO da finire che qui da problemi quando viene eseguito Pit Mutuation Testing
    	////mettere blocco try catch dentro dove si genererebbe l'eccezione e in quel punto mettere un brakePoint
	}
}



