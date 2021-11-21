package com.example.spring.demo.repositories.appointment;

import com.example.spring.demo.model.appoitmnent.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
