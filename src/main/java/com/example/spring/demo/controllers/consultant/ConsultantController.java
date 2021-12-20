package com.example.spring.demo.controllers.consultant;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.services.consultant.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/consultant")
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


    @PutMapping(value = "/putConsultant")
    public Consultant putConsultant(@RequestBody ConsultantDTO consultant) {

        Consultant c = new Consultant();
        c.setId(consultant.getId());
        c.setFirstName(consultant.getFirstName());
        c.setLastName(consultant.getLastName());
        c.setAppointments(consultant.getAppointments());
        this.consultantService.insertNewConsultant(c);
        return c;
    }

    @PutMapping(value = "/updateConsultant")
    public Consultant updateConsultant(@RequestBody ConsultantDTO consultant) {
        Consultant c = new Consultant();
        c.setId(consultant.getId());
        c.setFirstName(consultant.getFirstName());
        c.setLastName(consultant.getLastName());
        c.setAppointments(consultant.getAppointments());
        this.consultantService.updateConsultantById(c);
        return c;
    }


    @DeleteMapping("/{idConsultant}")
    public void deleteConsultant(@PathVariable Long idConsultant) {
        this.consultantService.deleteConsultantById(idConsultant);
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleNullPointerException(Exception e) {
        String error = "";
        error = e.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(error, status);
    }

}
