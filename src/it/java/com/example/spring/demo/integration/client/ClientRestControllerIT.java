package com.example.spring.demo.integration.client;
package com.examples.spring.demo;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.example.spring.demo.repositories.client.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;


import io.restassured.RestAssured;
import io.restassured.response.Response;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClientRestControllerIT {

    @Autowired
    private ClientRepository clientRepository;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.port = port;
        // always start with an empty database
        clientRepository.deleteAll();
        clientRepository.flush();
    }

    @Test
    public void testNewClient() throws Exception {
        Response response = given().
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(new Client(null, "new client", 1000)).
                when().
                post("/api/clients/new");

        Client saved = response.getBody().as(Client.class);

        assertThat(clientRepository.findById(saved.getId()))
                .contains(saved);
    }

    @Test
    public void testUpdateClient() throws Exception {
        // create an client with the repository
        Client saved = clientRepository
                .save(new Client(null, "original name", 1000));

        // modify it with PUT
        given().
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(new Client(null, "modified name", 2000)).
                when().
                put("/api/clients/update/" + saved.getId()).
                then().
                statusCode(200).
                body(
                        // in the JSON response the id is an integer
                        "id", equalTo(saved.getId().intValue()),
                        "name", equalTo("modified name"),
                        "salary", equalTo(2000)
                );
    }
}