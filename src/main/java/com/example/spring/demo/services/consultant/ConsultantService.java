package com.example.spring.demo.services.consultant;

import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.repositories.consultant.ConsultantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

	public Consultant updateConsultantById(Consultant replacement) {

		return consultantRepository.save(replacement);
	}

	
	public Consultant deleteConsultantById(Long idConsultant) {
		consultantRepository.delete(consultantRepository.getById(idConsultant));
		return null;		
	}
}
