package com.example.spring.demo.consultant;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONException;
import org.json.JSONObject;


import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.DockerComposeContainer;

import org.testcontainers.containers.wait.strategy.Wait;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;




import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import java.io.File;
import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConsultantDockerComposeTestContainersE2E {




        @SuppressWarnings("rawtypes")
        @Container
        public static DockerComposeContainer compose =
                new DockerComposeContainer(new File("./docker-compose.yml"))
                        .withExposedService("customerservice_1",8080)
                        .waitingFor("customerservice_1", Wait.forHttp("/spring-app/client/api/clients").forStatusCode(200).withStartupTimeout(Duration.ofMinutes(10)));


        private static WebDriver driver;

        private String baseUrl;



        @BeforeAll
        public static void setupClass() {
            WebDriverManager.chromedriver().setup();
        }


        @BeforeEach
        public void setup()  {
            baseUrl = "http://localhost:8080/spring-app";
            driver = new ChromeDriver();
        }


        @AfterAll
        public static void teardown() {
            compose.stop();
            driver.quit();
        }


        @Test
        void dockerComposeBehavior() throws JSONException, InterruptedException {

            Random rand = new Random();
            long generatedLong = rand.nextLong();
            postConsultant("test", generatedLong);
            driver.get(baseUrl + "/consultant/api/consultants");
            WebElement wb = driver.findElement(By.tagName("pre"));
            System.err.println(wb.getText());
            assertTrue(wb.getText().contains("test"));
            wb = driver.findElement(By.tagName("pre"));
            assertTrue(wb.getText().contains("test"));

        }


        private void postConsultant(String name, Long id) throws JSONException {
            JSONObject body = new JSONObject();
            body.put("firstName", name);
            body.put("id", id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity =
                    new HttpEntity<String>(body.toString(), headers);
            new RestTemplate()
                    .put(baseUrl + "/consultant/putConsultant", entity);

        }


    }
