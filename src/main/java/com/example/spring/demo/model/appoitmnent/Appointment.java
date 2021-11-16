package com.example.spring.demo.model.appoitmnent;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Appointment {


	@Id
	private Long id;
	private Long clientId;
	private Long consultantId;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
