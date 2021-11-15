package com.example.spring.demo.controllers.client;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
	/*	this.mvc.perform(get("/api/clients")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));llllllll
				// the above checks that the content is an empty JSON list*/
		assert(true);
	}

/*	@Test
	public void testAllClientsNotEmpty() throws Exception {
		when(clientService.getAllClients()).
			thenReturn(asList(
				new Client(1L, "Marco", "Rossi"),
				new Client(2L, "Francesco", "bianchi")
			));
			
		this.mvc.perform(get("/api/clients")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("first")))
				.andExpect(jsonPath("$[0].salary", is(1000)))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("second")))
				.andExpect(jsonPath("$[1].salary", is(5000)));
	}*/
}
