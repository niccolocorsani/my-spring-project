package com.example.spring.demo.unit.other;


import com.example.spring.demo.MySpringProjectApplication;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SpringRun {


    @Test
    public void start(){

      ConfigurableApplicationContext configurableApplicationContext =  SpringApplication.run(MySpringProjectApplication.class, null);
    }

}
