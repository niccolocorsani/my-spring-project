package com.example.spring.demo.integration;

import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.repositories.consultant.ConsultantRepository;
import com.example.spring.demo.services.consultant.ConsultantService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(ConsultantService.class)
public class ConsultantServiceRepositoryIT {


    @Autowired
    private ConsultantService consultantService;

    @Autowired
    private ConsultantRepository consultantRepository;

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void testServiceCanInsertIntoRepository() {
        Consultant saved = consultantService.insertNewConsultant(new Consultant(1L, "Marco", "Rossi"));
        assertThat(consultantRepository.findById(saved.getId())).isPresent();
    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void testServiceCanUpdateRepository() {
        Consultant saved = consultantRepository.save(new Consultant(1L, "Marco", "Rossi"));
        Consultant modified = consultantService.updateConsultantById(saved.getId(), new Consultant(saved.getId(), "modified", ""));
        assertThat(consultantRepository.findById(saved.getId())).contains(modified);
    }


    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void testServiceDeleteConsultantByID() {
        Consultant saved = consultantService.insertNewConsultant(new Consultant(1L, "Marco", "Rossi"));
        consultantService.deleteConsultantById(1L);
        System.err.println(consultantService.getConsultantById(1L));
        assertNull(consultantService.getConsultantById(saved.getId()));
    }

}
