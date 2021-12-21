package com.example.spring.demo.integration.consultant;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.repositories.consultant.ConsultantRepository;
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
public class ConsultantRestControllerIT {

    @Autowired
    private ConsultantRepository consultantRepository;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.port = port;
        consultantRepository.deleteAll();
        consultantRepository.flush();
    }

    @Test
    public void testNewConsultant() throws Exception {
        Response response = given().
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(new Consultant(null, "consultant", "test")).
                when().
                put("/spring-app/consultant/putConsultant");

        Consultant saved = response.getBody().as(Consultant.class);


        assertEquals(consultantRepository.findById(saved.getId()).get().getFirstName(),saved.getFirstName());
    }

    @Test
    public void testUpdateConsultant() throws Exception {

        given().
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(new Consultant(1l, "test", "test")).
                when().
                put("/spring-app/consultant/putConsultant").
                then().
                statusCode(200).
                body(
                        "firstName", equalTo("test"),
                        "firstName", equalTo("test")
                );
    }


}