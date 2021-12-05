package com.example.spring.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
            appointment.setDate(Date.valueOf("2021-11-10"));
            appointment.setStartTime(Time.valueOf("10:00:00"));
            appointment.setEndTime(Time.valueOf("11:00:00"));



            Appointment appointment1 = new Appointment();
            appointment1.setDate(Date.valueOf("2021-11-11"));
            appointment1.setStartTime(Time.valueOf("10:00:00"));
            appointment1.setEndTime(Time.valueOf("11:00:00"));



            Appointment appointment2 = new Appointment();
            appointment2.setDate(Date.valueOf("2021-11-14"));
            appointment2.setStartTime(Time.valueOf("10:00:00"));
            appointment2.setEndTime(Time.valueOf("11:00:00"));


            appointmentRepository.save(appointment);
            appointmentRepository.save(appointment1);
            appointmentRepository.save(appointment2);



            consultant.addAppointment(appointment);
            consultant.addAppointment(appointment1);
            consultant.addAppointment(appointment2);

            List<Appointment> l = appointmentRepository.findAll();

            consultantRepository.save(consultant);

            Optional<Consultant> cc = consultantRepository.findById(1L);



            List<Consultant> c = consultantRepository.findAll();
           Appointment a = consultantRepository.findAll().get(0).getAppointments().get(0);
        };
    }*/
}