package com.example.spring.demo.unit.controllers.client;


import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.spring.demo.controllers.client.ClientController;
import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.services.client.ClientService;



@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
    private ClientService clientService;



	@Test
	public void testAllClientsEmpty() throws Exception {
		System.err.println("oo");

		this.mvc.perform(get("/api/clients")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));
				// the above checks that the content is an empty JSON list
		assert(true);
	}

	@Test
	public void testAllClientsNotEmpty() throws Exception {
		
		List<Client> clients = new ArrayList<>();
		clients.add(new Client(1L, "Marco", "Rossi"));
		clients.add(new Client(2L, "Francesco", ""));
		System.err.println("oo");

		when(clientService.getAllClients()).
			thenReturn(clients);
	
		
		this.mvc.perform(get("/api/clients")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].firstName", is("Marco")))
				.andExpect(jsonPath("$[0].lastName", is("Rossi")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].firstName", is("Francesco")));
	}
}
