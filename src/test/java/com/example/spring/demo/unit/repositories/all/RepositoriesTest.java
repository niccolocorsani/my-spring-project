package com.example.spring.demo.unit.repositories.all;

import com.example.spring.demo.model.appoitmnent.Appointment;
import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.repositories.appointment.AppointmentRepository;
import com.example.spring.demo.repositories.client.ClientRepository;
import com.example.spring.demo.repositories.consultant.ConsultantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Time;


@DataJpaTest
@RunWith(SpringRunner.class)
public class RepositoriesTest {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ConsultantRepository consultantRepository;


    @Test
    public void getAppointmentTest() {

        Client client = new Client(1L, "Francesco", "Renga");
        clientRepository.save(client);
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDate(Date.valueOf("2021-03-10"));
        appointment.setStartTime(Time.valueOf("10:00:00"));
        appointment.setEndTime(Time.valueOf("11:00:00"));
        appointmentRepository.save(appointment);
        Assertions.assertNull(appointmentRepository.findAll().get(0).getClient());
        Assertions.assertNull(appointmentRepository.findAll().get(0).getConsultant());
     /*   Consultant consultant = new Consultant();
        consultant.setId(1L);
        consultant.setFirstName("Marco");
        consultant.setLastName("Rossi");
        consultantRepository.save(consultant);
        appointment.setConsultant(consultant);*/
        appointment.setClient(client);
        Assertions.assertNull(appointmentRepository.findAll().get(0).getClient());
        appointmentRepository.save(appointment);
        System.err.println(appointmentRepository.findAll().get(0).getConsultant().getFirstName());
        Assertions.assertNotNull(appointmentRepository.findAll().get(0).getClient());
        Assertions.assertNotNull(appointmentRepository.findAll().get(0).getConsultant());
//        Assertions.assertEquals(appointmentRepository.findAll().get(0).getClient().getFirstName(),"Francesco");

    }

}
