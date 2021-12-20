package com.example.spring.demo.unit.controllers.consultant;

import com.example.spring.demo.controllers.consultant.ConsultantController;
import com.example.spring.demo.model.appoitmnent.Appointment;
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
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ConsultantController.class)
@Import(ConsultantController.class)
class ConsultantControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ConsultantService consultantService;

	@Test
	void testAllConsultantsEmpty() throws Exception {

		this.mvc.perform(get("/consultant/api/consultants").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json("[]"));
		// the above checks that the content is an empty JSON list
		assert (true);
	}

	@Test
	void testAllConsultantsNotEmpty() throws Exception {

		List<Consultant> consultants = new ArrayList<>();
		consultants.add(new Consultant(1L, "Marco", "Rossi"));
		consultants.add(new Consultant(2L, "Francesco", ""));

		when(consultantService.getAllConsultants()).thenReturn(consultants);

		this.mvc.perform(get("/consultant/api/consultants").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].firstName", is("Marco"))).andExpect(jsonPath("$[0].lastName", is("Rossi")))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].firstName", is("Francesco")));
	}

	
	@Test
	void testControllerGetConsultant() throws Exception {

		Consultant c = new Consultant(1L, "Marco", "Rossi");
		Appointment appointment = new Appointment();
		c.addAppointment(appointment);
		when(this.consultantService.getConsultantById(1L)).thenReturn(c);
		MvcResult result = this.mvc.perform(get("/consultant/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		Consultant consultant = new ObjectMapper().readValue(json, Consultant.class);
		assertEquals(consultant.getFirstName(), c.getFirstName());
		assertEquals(consultant.getLastName(), c.getLastName());
		assertEquals(consultant.getId(), c.getId());
	}

	@Test
	void testControllerPutConsultant() throws Exception {

		Consultant consultant = new Consultant();
		consultant.setId(1L);
		consultant.setFirstName("Marco");
		consultant.setUserName("user");
		ObjectMapper mapper = new ObjectMapper();
		String consultantString = mapper.writeValueAsString(consultant);
		this.mvc.perform(put("/consultant/putConsultant").contentType(MediaType.APPLICATION_JSON)
				.content(consultantString).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("id", is(1))).andExpect(jsonPath("firstName", is("Marco")))
                .andExpect(jsonPath("userName", is("user")));

	}

	@Test
	void testControllerPutAndUpdateConsultant() throws Exception {

		Consultant consultant = new Consultant();
		consultant.setId(1L);
		consultant.setFirstName("Marco");
		ObjectMapper mapper = new ObjectMapper();
		String consultantString = mapper.writeValueAsString(consultant);
		this.mvc.perform(put("/consultant/putConsultant"));
		consultant.setFirstName("Andrea");
		consultantString = mapper.writeValueAsString(consultant);
		this.mvc.perform(put("/consultant/updateConsultant").contentType(MediaType.APPLICATION_JSON)
				.content(consultantString).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("id", is(1))).andExpect(jsonPath("firstName", is("Andrea")));

	}

	@Test
	void testControllerPutAndDeleteConsultant() throws Exception {

		Consultant consultant = new Consultant();
		consultant.setId(1L);
		consultant.setFirstName("Marco");
		ObjectMapper mapper = new ObjectMapper();
		String consultantString = mapper.writeValueAsString(consultant);
		this.mvc.perform(put("/consultant/putConsultant").contentType(MediaType.APPLICATION_JSON)
				.content(consultantString).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		when(this.consultantService.deleteConsultantById(1L)).thenReturn(null);
		MvcResult resultDelete = this.mvc.perform(delete("/consultant/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String json = resultDelete.getResponse().getContentAsString();

		assertThrows(Exception.class, () -> {
			new ObjectMapper().readValue(json, Consultant.class);
		});

	}

	@Test
	void handleError() throws Exception {

		MvcResult result = this.mvc
				.perform(put("/consultant/putConsultant").contentType(MediaType.APPLICATION_JSON).content("wrong-content")).andReturn();
		String json = result.getResponse().getContentAsString();
		assertTrue(json.length()>0);
	}
}
