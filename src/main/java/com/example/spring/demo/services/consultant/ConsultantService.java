package com.example.spring.demo.services.consultant;

import java.util.List;

import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.repositories.consultant.ConsultantRepository;

public class ConsultantService {

	
	private ConsultantRepository consultantRepository;

	public ConsultantService(ConsultantRepository consultantRepository) {
		this.consultantRepository = consultantRepository;
	}

	public List<Consultant> getAllConsultants() {
		return consultantRepository.findAll();
	}

	public Consultant getConsultantById(long id) {
		return consultantRepository.findById(id).orElse(null);
	}

	public Consultant insertNewConsultant(Consultant consultant) {
		return consultantRepository.save(consultant);
	}

	public Consultant updateConsultantById(long id, Consultant replacement) {
		replacement.setId(id);
		return consultantRepository.save(replacement);
	}

	public void deleteUserById(Long idConsultant) {
		consultantRepository.delete(consultantRepository.getById(idConsultant));		
	}
}
