package com.example.spring.demo;

import com.example.spring.demo.model.appoitmnent.Appointment;
import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.repositories.appointment.AppointmentRepository;
import com.example.spring.demo.repositories.client.ClientRepository;
import com.example.spring.demo.repositories.consultant.ConsultantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MySpringProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringProjectApplication.class, args);
    }


 /* @Bean
    public CommandLineRunner loadData( AppointmentRepository appointmentRepository, ConsultantRepository consultantRepository) {
        return (args) -> {

            Consultant consultant = new Consultant();
            consultant.setId(1L);
            consultant.setFirstName("Marco");
            consultant.setLastName("Bianchi");
            consultant.setUserName("userName12");
            Appointment appointment = new Appointment();
            appointment.setId(1L);
            appointment.setDate(Date.valueOf("2021-11-10"));
            appointment.setStartTime(Time.valueOf("10:00:00"));
            appointment.setEndTime(Time.valueOf("11:00:00"));

            appointmentRepository.save(appointment);

            consultant.addAppointment(appointment);

            List<Appointment> l = appointmentRepository.findAll();

            consultantRepository.save(consultant);

            Optional<Consultant> cc = consultantRepository.findById(1L);



            List<Consultant> c = consultantRepository.findAll();
           Appointment a = consultantRepository.findAll().get(0).getAppointments().get(0);
        };
    }*/
}