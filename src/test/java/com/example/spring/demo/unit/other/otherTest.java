package com.example.spring.demo.unit.other;


import com.example.spring.demo.MySpringProjectApplication;
import com.example.spring.demo.controllers.client.ClientController;
import com.example.spring.demo.controllers.consultant.ConsultantController;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
 class otherTest {


    @Test
     void start() {
        String[] args= {"test1","test2"};
        assertDoesNotThrow(() -> {
            MySpringProjectApplication.main(args);
        });
    }



    @Test
     void controllersInstances() {

        ClientController clientController = new ClientController();
        assertNotNull(clientController);
        ConsultantController consultantController = new ConsultantController();
        assertNotNull(consultantController);

    }
}
