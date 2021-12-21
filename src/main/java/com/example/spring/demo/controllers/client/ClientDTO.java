package com.example.spring.demo.controllers.client;

import com.example.spring.demo.model.appoitmnent.Appointment;
import com.example.spring.demo.parentModel.ParentModel;

import java.util.ArrayList;
import java.util.List;

public class ClientDTO extends ParentModel {

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private List<Appointment> appointments = new ArrayList<>();


}
