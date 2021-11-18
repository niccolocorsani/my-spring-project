package com.example.spring.demo.unit.services.client;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

 //// Questo è compatibile con RunWith....
///import org.junit.jupiter.api.Test;; //// Questo è compatibile con ExtendWith....

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.repositories.client.ClientRepository;
import com.example.spring.demo.services.client.ClientService;

@RunWith(MockitoJUnitRunner.class)
public class ClientWithServiceMockitoTest {

	@Mock
	private ClientRepository clientRepository;

	@InjectMocks
	private ClientService clientService;

	@Test
	public void test_getAllClients() {
		Client client1 = new Client();
		client1.setId(1L);
		client1.setFirstName("Marco");
		client1.setLastName("Rossi");
		Client client2 = new Client(2L, "Francesco", "Bianchi");
		when(clientRepository.findAll()).thenReturn(asList(client1, client2));
		assertThat(clientService.getAllClients()).containsExactly(client1, client2);
	}

	@Test
	public void test_getClientById_found() {
		Client client = new Client(1L, "Marco", "Rossi");
		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
		assertThat(clientService.getClientById(1L)).isSameAs(client);
	}

}