package com.example.spring.demo.controllers.client;

import com.example.spring.demo.model.appoitmnent.Appointment;

import java.util.ArrayList;
import java.util.List;

public class ClientDTO {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private List<Appointment> appointments = new ArrayList<>();

}
