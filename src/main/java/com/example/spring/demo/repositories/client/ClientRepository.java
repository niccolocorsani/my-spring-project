package com.example.spring.demo.repositories.client;

import com.example.spring.demo.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client, Long>{


	Client findByFirstName(String string);


}
