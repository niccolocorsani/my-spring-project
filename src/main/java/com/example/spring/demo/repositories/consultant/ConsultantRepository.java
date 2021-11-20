package com.example.spring.demo.repositories.consultant;

import com.example.spring.demo.model.consultant.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultantRepository  extends JpaRepository<Consultant, Long>{

	
	Consultant findByFirstName(String string);

}
