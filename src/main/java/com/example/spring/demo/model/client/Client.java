package com.example.spring.demo.model.client;
import com.example.spring.demo.model.appoitmnent.Appointment;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CheckRegularExperssion
	public String firstName;

	public String lastName;

	public String userName;


	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL,fetch=FetchType.EAGER) ///necessario fetch eager perchè se si chiude la sessione del controller prima che si carichi da errore
	private List<Appointment> appointments = new ArrayList<>();

	public Client(Long id, String firstName, String lastName) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;

	}


	public Client() {
		// Do nothing because aim is to provide an empty constructor.
	}
	


	
}
