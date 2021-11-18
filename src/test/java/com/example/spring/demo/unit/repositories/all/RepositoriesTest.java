package com.example.spring.demo.unit.repositories.all;

import com.example.spring.demo.model.appoitmnent.Appointment;
import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.repositories.appointment.AppointmentRepository;
import com.example.spring.demo.repositories.client.ClientRepository;
import com.example.spring.demo.repositories.consultant.ConsultantRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


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
   // @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void getAppointmentWithClientTest() {

        Client  client = new Client(1L, "Francesco", "Renga");
        Consultant consultant = new Consultant();
        consultant.setId(1L);
        consultant.setFirstName("Marco");
        consultant.setLastName("Bianchi");
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDate(Date.valueOf("2021-03-10"));
        appointment.setStartTime(Time.valueOf("10:00:00"));
        appointment.setEndTime(Time.valueOf("11:00:00"));



        clientRepository.save(client);
        appointmentRepository.save(appointment);
        appointment.setClient(client);
        assertNull(appointmentRepository.findAll().get(0).getClient());
        appointmentRepository.save(appointment);
        System.err.println(appointmentRepository.findAll().get(0).getClient());
        assertNotNull(appointmentRepository.findAll().get(0).getClient());
    }
    /*   @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void getAppointmentWithConsultantTest() {
       consultantRepository.save(consultant);
        appointmentRepository.save(appointment);
        Assertions.assertNull(appointmentRepository.findAll().get(0).getConsultant());
        appointment.setConsultant(consultant);
        Assertions.assertNull(appointmentRepository.findAll().get(0).getConsultant());
        appointmentRepository.save(appointment);
        Assertions.assertNotNull(appointmentRepository.findAll().get(0).getConsultant());
    }*/


   /* @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void getConsultantWithAppointmentTest() {


        Client  client = new Client(1L, "Francesco", "Renga");
        Consultant consultant = new Consultant();
        consultant.setId(1L);
        consultant.setFirstName("Marco");
        consultant.setLastName("Bianchi");
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDate(Date.valueOf("2021-03-10"));
        appointment.setStartTime(Time.valueOf("10:00:00"));
        appointment.setEndTime(Time.valueOf("11:00:00"));


        appointmentRepository.save(appointment);
        consultant.setAppointments(appointmentRepository.findAll());
        consultantRepository.save(consultant);
        assertNotNull(consultantRepository.findAll().get(0).getAppointments().get(0));

    }*/
}
