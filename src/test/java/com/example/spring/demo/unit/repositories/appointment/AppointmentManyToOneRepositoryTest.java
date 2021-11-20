package com.example.spring.demo.unit.repositories.appointment;

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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;



@DataJpaTest
@RunWith(SpringRunner.class)
public class AppointmentManyToOneRepositoryTest {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ConsultantRepository consultantRepository;

    @Autowired
    private ClientRepository clientRepository;

    private Client client;



    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    void getAppointmentWithConsultantTest() throws InterruptedException {

        Consultant consultant = new Consultant();
        consultant.setId(1L);
        consultant.setFirstName("Marco");
        consultant.setLastName("Bianchi");
        Appointment  appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDate(Date.valueOf("2021-03-10"));
        appointment.setStartTime(Time.valueOf("10:00:00"));
        appointment.setEndTime(Time.valueOf("11:00:00"));

        consultantRepository.save(this.consultant);
        this.appointment.setConsultant(this.consultant);
        this.appointmentRepository.save(this.appointment);
        List appointments = this.appointmentRepository.findAll();
        Consultant cons = this.appointmentRepository.findAll().get(0).getConsultant();
        assertNotNull(this.appointmentRepository.findAll().get(0).getConsultant());

    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    void getAppointmentWithClientTest() {

        this.client = new Client(1L, "Francesco", "Renga");
        this.appointment = new Appointment();
        this.appointment.setId(1L);
        this.appointment.setDate(Date.valueOf("2021-03-10"));
        this.appointment.setStartTime(Time.valueOf("10:00:00"));
        this.appointment.setEndTime(Time.valueOf("11:00:00"));
        this.clientRepository.save(client);
        this.appointmentRepository.save(this.appointment);
        this.appointment.setClient(client);
        assertNull(this.appointmentRepository.findAll().get(0).getClient());
        this.appointmentRepository.save(this.appointment);
        assertNotNull(this.appointmentRepository.findAll().get(0).getClient());


    }


}
