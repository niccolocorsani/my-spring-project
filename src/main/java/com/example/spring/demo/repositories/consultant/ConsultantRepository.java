package com.example.spring.demo.repositories.consultant;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.demo.model.consultant.Consultant;

public interface ConsultantRepository  extends JpaRepository<Consultant, Long>{

	
	Consultant findByFirstName(String string);

}
