package com.example.spring.demo.model.appoitmnent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.spring.demo.model.client.Client;

@Entity
public class Appointment {


	@Id
	private Long id;

	@ManyToOne
	private Client client;
	


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
