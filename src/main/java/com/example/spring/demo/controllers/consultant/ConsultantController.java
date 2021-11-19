package com.example.spring.demo.controllers.consultant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.services.consultant.ConsultantService;
import com.example.spring.demo.services.consultant.ConsultantService;

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
        Consultant consultant = this.consultantService.getConsultantById(idConsultant);
        return consultant;
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
