package com.example.spring.demo.model.appoitmnent;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.model.consultant.Consultant;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Appointment {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Date date;

	@Column
	private Time startTime;

	@Column
	private Time endTime;

	@ManyToOne
	private Client client;
	
	@ManyToOne
	private Consultant consultant;

	public Appointment() {
		// Do nothing because aim is to provide an empty constructor.
	}



	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Consultant getConsultant() {
		return consultant;
	}

	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}


}
