package com.example.spring.demo.unit.other;


import com.example.spring.demo.MySpringProjectApplication;
import com.example.spring.demo.controllers.client.ClientController;
import com.example.spring.demo.controllers.consultant.ConsultantController;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class SpringRun {


    @Test
    public void start() {

        MySpringProjectApplication mySpringProjectApplication = new MySpringProjectApplication();
        mySpringProjectApplication.main(null);

    }



    @Test
    public void controllersInstances() {

        ClientController clientController = new ClientController();
        assertNotNull(clientController);
        ConsultantController consultantController = new ConsultantController();
        assertNotNull(consultantController);

    }
}
