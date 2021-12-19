package com.example.spring.demo.integration.client;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.repositories.client.ClientRepository;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 class ClientMySQLRepositoryTestContainersIT {


    @Autowired
    private ClientRepository clientRepository;


    @SuppressWarnings("rawtypes")
	@Container
    public static MySQLContainer container = new MySQLContainer()
            .withDatabaseName("test");


    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> "jdbc:mysql://" + container.getContainerIpAddress() + ":" + container.getMappedPort(3306) + "/" + "test?useSSL=false");
        System.err.println(container.getContainerIpAddress());
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");

    }


    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    void saveClientTest() throws IOException, InterruptedException {
        Client client = new Client(1L, "test", "test");
        Client saved = clientRepository.save(client);
        assertEquals(saved.getFirstName(), client.getFirstName());
    }


    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    void getClientTest() {
        Client client = new Client(1L, "test", "test");
        clientRepository.save(client);
        assertEquals(clientRepository.getById(1L).getFirstName(), client.getFirstName());
    }

}