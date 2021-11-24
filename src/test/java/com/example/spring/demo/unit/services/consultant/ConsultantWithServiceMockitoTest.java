package com.example.spring.demo.unit.services.consultant;

import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.repositories.consultant.ConsultantRepository;
import com.example.spring.demo.services.consultant.ConsultantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
 class ConsultantWithServiceMockitoTest {

	@Mock
	private ConsultantRepository consultantRepository;

	@InjectMocks
	private ConsultantService consultantService;

	@Test
	 void test_getAllConsultants() {
		Consultant consultant1 = new Consultant(1L, "Marco", "Rossi");
		consultant1.setUserName("MarcoProva");
		Consultant consultant2 = new Consultant(2L, "Francesco", "Bianchi");
		consultant2.setUserName("FrancescoProva");
		when(consultantRepository.findAll()).thenReturn(asList(consultant1, consultant2));
		assertThat(consultantService.getAllConsultants()).containsExactly(consultant1, consultant2);
	}

	@Test
	 void test_getConsultantById_found() {
		Consultant consultant = new Consultant(1L, "Marco", "Rossi");
		when(consultantRepository.findById(1L)).thenReturn(Optional.of(consultant));
		assertThat(consultantService.getConsultantById(1L)).isSameAs(consultant);
	}


}
