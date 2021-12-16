package com.example.spring.demo.integration.consultant;

import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.repositories.consultant.ConsultantRepository;
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


import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 class ConsultantMySQLRepositoryTestContainersIT {


    @Autowired
    private ConsultantRepository consultantRepository;


    @Container
    public static MySQLContainer container = new MySQLContainer()
            .withUsername("operations")
            .withPassword("operations")
            .withDatabaseName("test");


    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> "jdbc:mysql://" + container.getContainerIpAddress() + ":" + container.getMappedPort(3306) + "/" + "test?useSSL=false");
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");

    }


    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    void saveConsultantTest() {
        Consultant consultant = new Consultant(1L, "test", "test");
        Consultant saved = consultantRepository.save(consultant);
        assertEquals(saved.getFirstName(),consultant.getFirstName());
    }


    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    void getConsultantTest() {
        Consultant consultant = new Consultant(1L, "test", "test");
        consultantRepository.save(consultant);
        assertEquals(consultantRepository.getById(1L).getFirstName(),consultant.getFirstName());
    }

}