package com.example.spring.demo.model.consultant;

import com.example.spring.demo.model.appoitmnent.Appointment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Consultant {

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

	public void addAppointment(Appointment appointment) {
		this.appointments.add(appointment);
		//best practice set in both direction
		appointment.setConsultant(this);
	}

}
