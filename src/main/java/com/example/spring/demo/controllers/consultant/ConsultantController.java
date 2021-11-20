package com.example.spring.demo.controllers.consultant;

import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.services.consultant.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsultantController {
	
	
	@Autowired
	private ConsultantService consultantService;

	@GetMapping("/api/consultants")
	public List<Consultant> getAllConsultants() {
		return consultantService.getAllConsultants();
	}

	
    @GetMapping(value = "/{idConsultant}")
    public Consultant getConsultant(@PathVariable("idConsultant") Long idConsultant) {
        return this.consultantService.getConsultantById(idConsultant);
    }
    
    
    @PutMapping( value = "/putConsultant")
    public Consultant putConsultant(@RequestBody Consultant consultant) {
        this.consultantService.insertNewConsultant(consultant);
        return consultant;
    }

    @PutMapping( value = "/updateConsultant")
    public Consultant updateConsultant(@RequestBody Consultant consultant) {
        this.consultantService.updateConsultantById(consultant.getId(), consultant);
        return consultant;
    }
    
    
    
    @DeleteMapping("/{idConsultant}")
    public void deleteConsultant(@PathVariable Long idConsultant) {
        this.consultantService.deleteConsultantById(idConsultant);
    }
    
   
}
