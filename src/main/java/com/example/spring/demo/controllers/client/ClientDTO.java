package com.example.spring.demo.controllers.client;

import com.example.spring.demo.model.appoitmnent.Appointment;
import com.example.spring.demo.parentModel.ParentModelClass;

import java.util.ArrayList;
import java.util.List;

public class ClientDTO extends ParentModelClass {

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private List<Appointment> appointments = new ArrayList<>();


}
