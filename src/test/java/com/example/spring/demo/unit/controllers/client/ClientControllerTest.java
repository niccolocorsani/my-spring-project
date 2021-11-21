package com.example.spring.demo.unit.controllers.client;



import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import com.example.spring.demo.controllers.client.ClientController;
import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.services.client.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClientController.class)
class ClientControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ClientService clientService;

	@Test
	void testAllClientsEmpty() throws Exception {

		this.mvc.perform(get("/api/clients").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("[]"));
	}

	@Test
	void testAllClientsNotEmpty() throws Exception {

		List<Client> clients = new ArrayList<>();
		clients.add(new Client(1L, "Marco", "Rossi"));
		clients.add(new Client(2L, "Francesco", ""));

		when(clientService.getAllClients()).thenReturn(clients);

		this.mvc.perform(get("/api/clients").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].firstName", is("Marco")))
				.andExpect(jsonPath("$[0].lastName", is("Rossi"))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].firstName", is("Francesco")));
	}

	@Test
	void testControllerGetClient() throws Exception {
	
		Client c  = new Client(1L, "Marco", "Rossi");
		when(clientService.getClientById(1L)).thenReturn(c);
		
		MvcResult result =this.mvc.perform(get("/1").accept(MediaType.APPLICATION_JSON))
				  .andExpect(status().isOk()).andReturn();    
          System.err.println(result.getResponse().getContentAsString());
		  String json =  result.getResponse().getContentAsString();
		  Client client = new ObjectMapper().readValue(json, Client.class);
		  assertEquals(client.getFirstName(), c.getFirstName());
		  assertEquals(client.getLastName(), c.getLastName());
		  assertEquals(client.getId(), c.getId());
	}
	

	@Test
	void testControllerPutClient() throws Exception {

		Client client = new Client();
		client.setId(1L);
		client.setFirstName("Marco");
		ObjectMapper mapper = new ObjectMapper();
		String clientString = mapper.writeValueAsString(client);
		this.mvc.perform(put("/putClient").contentType(MediaType.APPLICATION_JSON).content(clientString)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("id", is(1)))
				.andExpect(jsonPath("firstName", is("Marco")));

	}

	@Test
	void testControllerPutAndUpdateClient() throws Exception {

		Client client = new Client();
		client.setId(1L);
		client.setFirstName("Marco");
		ObjectMapper mapper = new ObjectMapper();
		String clientString = mapper.writeValueAsString(client);
		this.mvc.perform(put("/putClient"));
		client.setFirstName("Andrea");
		clientString = mapper.writeValueAsString(client);
		this.mvc.perform(put("/updateClient").contentType(MediaType.APPLICATION_JSON).content(clientString)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("id", is(1)))
				.andExpect(jsonPath("firstName", is("Andrea")));

	}

	@Test
	void testControllerPutAndDeleteClient() throws Exception {

		Client client = new Client();
		client.setId(1L);
		client.setFirstName("Marco");
		ObjectMapper mapper = new ObjectMapper();
		String clientString = mapper.writeValueAsString(client);
		this.mvc.perform(put("/putClient").contentType(MediaType.APPLICATION_JSON).content(clientString)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		this.mvc.perform(delete("/1")).andExpect(status().isOk());

	}

}
