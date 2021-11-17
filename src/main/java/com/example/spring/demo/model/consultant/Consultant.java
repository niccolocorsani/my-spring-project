package com.example.spring.demo.model.consultant;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.spring.demo.model.appoitmnent.Appointment;

@Entity
public class Consultant {

	@Id
	@GeneratedValue
	private Long id;

	private String firstName;

	private String lastName;

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
	private List<Appointment> appointments;
	

	public Consultant(Long id, String firstName, String lastName) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	public Consultant() {
	}

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
