package com.example.spring.demo.unit.repositories.all;

import com.example.spring.demo.model.appoitmnent.Appointment;
import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.repositories.appointment.AppointmentRepository;
import com.example.spring.demo.repositories.client.ClientRepository;
import com.example.spring.demo.repositories.consultant.ConsultantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@RunWith(SpringRunner.class)
public class RepositoriesTest {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ConsultantRepository consultantRepository;


    private Consultant consultant;
    private Client client;
    private Appointment appointment;


    @BeforeEach
    public void serUp() {

        this.client = new Client(1L, "Francesco", "Renga");
        this.consultant = new Consultant();
        this.consultant.setId(1L);
        this.consultant.setFirstName("Marco");
        this.consultant.setLastName("Bianchi");
        this.appointment = new Appointment();
        this.appointment.setId(1L);
        this.appointment.setDate(Date.valueOf("2021-03-10"));
        this.appointment.setStartTime(Time.valueOf("10:00:00"));
        this.appointment.setEndTime(Time.valueOf("11:00:00"));
    }


   /* @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void getAppointmentWithConsultantTest() {
        this.consultantRepository.save(this.consultant);
        this.appointmentRepository.save(this.appointment);
        assertNull(this.appointmentRepository.findAll().get(0).getConsultant());
        this.appointment.setConsultant(this.consultant);
        assertNull(this.appointmentRepository.findAll().get(0).getConsultant());
        this.appointmentRepository.save(this.appointment);
        assertNotNull(this.appointmentRepository.findAll().get(0).getConsultant());
    }*/


    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    // questa annotazione serve a pulire il contesto alla fine di ogni metodo
    public void getAppointmentWithClientTest() {


        clientRepository.save(client);
        this.appointmentRepository.save(this.appointment);
        this.appointment.setClient(client);
        assertNull(this.appointmentRepository.findAll().get(0).getClient());
        this.appointmentRepository.save(this.appointment);
        System.err.println(this.appointmentRepository.findAll().get(0).getClient());
        assertNotNull(this.appointmentRepository.findAll().get(0).getClient());

    }


    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void getConsultantWithAppointmentTest() {

        this.appointmentRepository.saveAndFlush(this.appointment);
        this.consultant.setAppointments(this.appointmentRepository.findAll());

        this.consultantRepository.save(this.consultant);
        assertNotNull(this.consultantRepository.findAll().get(0).getAppointments().get(0));

    }


    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void getClientWithAppointmentTest() {

        this.appointmentRepository.save(this.appointment);

        this.client.setAppointments(this.appointmentRepository.findAll());
        this.clientRepository.save(this.client);
        this.appointment.setDate(Date.valueOf("2021-03-10"));

        assertNotNull(this.clientRepository.findAll().get(0).getAppointments().get(0));
        assertEquals(this.clientRepository.findAll().get(0).getAppointments().get(0).getDate(), Date.valueOf("2021-03-10"));
        assertEquals(this.clientRepository.findAll().get(0).getAppointments().get(0).getStartTime(), Time.valueOf("10:00:00"));
        assertEquals(this.clientRepository.findAll().get(0).getAppointments().get(0).getEndTime(), Time.valueOf("11:00:00"));

    }


}
