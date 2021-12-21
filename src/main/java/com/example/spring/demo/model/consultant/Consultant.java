package com.example.spring.demo.model.consultant;

import com.example.spring.demo.model.appoitmnent.Appointment;
import com.example.spring.demo.parentModel.ParentModelClass;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Consultant  extends ParentModelClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	private String userName;


	@OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Appointment> appointments = new ArrayList<>();
	

	public Consultant(Long id, String firstName, String lastName) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;


	}

	public Consultant() {
		// Do nothing because aim is to provide an empty constructor.
	}


	public void addAppointment(Appointment appointment) {
		this.appointments.add(appointment);
		//best practice set in both direction
		appointment.setConsultant(this);
	}

}
