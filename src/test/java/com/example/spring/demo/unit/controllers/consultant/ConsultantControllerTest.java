package com.example.spring.demo.unit.controllers.consultant;

import com.example.spring.demo.controllers.consultant.ConsultantController;
import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.services.consultant.ConsultantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ConsultantController.class)
@Import(ConsultantController.class)
public class ConsultantControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ConsultantService conultantService;

	@Test
	public void testAllConsultantsEmpty() throws Exception {

		this.mvc.perform(get("/api/consultants").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
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

		this.mvc.perform(get("/api/consultants").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].firstName", is("Marco")))
				.andExpect(jsonPath("$[0].lastName", is("Rossi"))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].firstName", is("Francesco")));
	}




	@Test
	public void testControllerGetConsultant() throws Exception {

		this.mvc.perform(get("/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


	@Test
	public void testControllerPutConsultant() throws Exception {

		Consultant consultant = new Consultant();
		consultant.setId(1L);
		consultant.setFirstName("Marco");
		ObjectMapper mapper = new ObjectMapper();
		String consultantString = mapper.writeValueAsString(consultant);
		this.mvc.perform(put("/putConsultant")
				.contentType(MediaType.APPLICATION_JSON)
				.content(consultantString)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("id", is(1)))
				.andExpect(jsonPath("firstName", is("Marco")));

	}


	@Test
	public void testControllerPutAndUpdateConsultant() throws Exception {

		Consultant consultant = new Consultant();
		consultant.setId(1L);
		consultant.setFirstName("Marco");
		ObjectMapper mapper = new ObjectMapper();
		String consultantString = mapper.writeValueAsString(consultant);
		this.mvc.perform(put("/putConsultant"));
		consultant.setFirstName("Andrea");
		consultantString = mapper.writeValueAsString(consultant);
		this.mvc.perform(put("/updateConsultant")
				.contentType(MediaType.APPLICATION_JSON)
				.content(consultantString)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("id", is(1)))
				.andExpect(jsonPath("firstName", is("Andrea")));

	}


	@Test
	public void testControllerPutAndDeleteConsultant() throws Exception {

		Consultant consultant = new Consultant();
		consultant.setId(1L);
		consultant.setFirstName("Marco");
		ObjectMapper mapper = new ObjectMapper();
		String consultantString = mapper.writeValueAsString(consultant);
		this.mvc.perform(put("/putConsultant")
				.contentType(MediaType.APPLICATION_JSON)
				.content(consultantString)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());


		this.mvc.perform(delete("/1"))
				.andExpect(status().isOk());

	}







}
