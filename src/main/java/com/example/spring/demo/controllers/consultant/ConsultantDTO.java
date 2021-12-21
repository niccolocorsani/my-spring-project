package com.example.spring.demo.controllers.consultant;

import com.example.spring.demo.model.appoitmnent.Appointment;
import com.example.spring.demo.parent_model.ParentModel;

import java.util.ArrayList;
import java.util.List;

public class ConsultantDTO extends ParentModel {


    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private List<Appointment> appointments = new ArrayList<>();


}
