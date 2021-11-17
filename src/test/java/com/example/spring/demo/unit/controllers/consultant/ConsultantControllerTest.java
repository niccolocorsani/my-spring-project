package com.example.spring.demo.unit.controllers.consultant;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.spring.demo.controllers.consultant.ConsultantController;
import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.services.consultant.ConsultantService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ConsultantController.class)
public class ConsultantControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ConsultantService conultantService;

	@Test
	public void testAllConsultantsEmpty() throws Exception {
		System.err.println("oo");

		this.mvc.perform(get("/api/conultants").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("[]"));
		// the above checks that the content is an empty JSON list
		assert (true);
	}

	@Test
	public void testAllConsultantsNotEmpty() throws Exception {

		List<Consultant> conultants = new ArrayList<>();
		conultants.add(new Consultant(1L, "Marco", "Rossi"));
		conultants.add(new Consultant(2L, "Francesco", ""));
		System.err.println("oo");

		when(conultantService.getAllConsultants()).thenReturn(conultants);

		this.mvc.perform(get("/api/conultants").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].firstName", is("Marco")))
				.andExpect(jsonPath("$[0].lastName", is("Rossi"))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].firstName", is("Francesco")));
	}
}