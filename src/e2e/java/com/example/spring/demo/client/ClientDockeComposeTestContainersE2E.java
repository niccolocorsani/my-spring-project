package com.example.spring.demo.client;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONException;
import org.json.JSONObject;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.DockerComposeContainer;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


import java.io.File;
import java.util.Random;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Testcontainers
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientDockeComposeTestContainersE2E {


    //static final Logger logger = LogManager.getLogger(ClientDockeComposeTestContainersE2E.class.getName());


    @SuppressWarnings("rawtypes")
    @Container
    public static DockerComposeContainer container =
            new DockerComposeContainer(new File("./docker-compose.yml"));


    private static WebDriver driver;

    private String baseUrl;


    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void setup() throws InterruptedException {


        baseUrl = "http://localhost:8080/spring-app";
        driver = new ChromeDriver();
        //container.start();
        boolean containerReady = false;
        int i = 0;
        while (containerReady != true) {
            try {
                Thread.sleep(10000); // wait for container to start
                postEmployee("test", 1L);
                containerReady = true;
            } catch (Exception exception) {
                exception.printStackTrace();
                System.err.println(containerReady);
            }
        }
    }

    @AfterAll
    public static void teardown() {
        //container.stop();
        driver.quit();
    }


    @Test
    void startAndStopContainerPersistenceTest() throws JSONException, InterruptedException {

        Random rand = new Random();
        Thread.sleep(1000);
        long generatedLong = rand.nextLong();
        postEmployee("test", generatedLong);
        Thread.sleep(1000);
        driver.get(baseUrl + "/client/api/clients");
        WebElement wb = driver.findElement(By.tagName("pre"));
        System.err.println(wb.getText());
        assertTrue(wb.getText().contains("test"));
    }

    private void postEmployee(String name, Long id) throws JSONException {
        JSONObject body = new JSONObject();
        body.put("firstName", name);
        body.put("id", id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity =
                new HttpEntity<String>(body.toString(), headers);
        new RestTemplate()
                .put(baseUrl + "/client/putClient", entity);

    }


}
