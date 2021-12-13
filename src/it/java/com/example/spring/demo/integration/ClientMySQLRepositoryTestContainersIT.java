package com.example.spring.demo.integration;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.repositories.client.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;



@Testcontainers
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientMySQLRepositoryTestContainersIT {

    @Container
    public static MySQLContainer container = new MySQLContainer()
            .withUsername("operations")
            .withPassword("operations")
            .withDatabaseName("test?useSSL=false");




    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.jpa.hibernate.ddl-auto", ()->  "create-drop");

    }

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void saveClient()  {

        Client client = new Client(1L, "test", "test");
        clientRepository.save(client);
        System.out.println("Context loads!");
    }
}