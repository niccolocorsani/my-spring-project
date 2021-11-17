package com.example.spring.demo.model.appoitmnent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.*;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.model.consultant.Consultant;

@Entity
public class Appointment {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Client client;
	
	@ManyToOne
	private Consultant consultant;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}