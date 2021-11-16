package com.example.spring.demo.repositories.client;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.demo.model.client.Client;

public interface ClientRepository  extends JpaRepository<Client, Long>{

	Client findByFirstName(String string);


}
