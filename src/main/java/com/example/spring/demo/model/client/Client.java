package com.example.spring.demo.model.client;


import java.util.List;

import javax.persistence.*;

import com.example.spring.demo.model.appoitmnent.Appointment;


@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;
	

    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Appointment> appointments;
	

	public Client(Long id, String firstName, String lastName) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}


	public Client() {}
	
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
	
	public List<Appointment> getAppointments() {
		return appointments;
	}


	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	
	
	
}
