package com.example.spring.demo.unit.repositories.consultant;

import com.example.spring.demo.model.consultant.Consultant;
import com.example.spring.demo.repositories.consultant.ConsultantRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;





@DataJpaTest
@RunWith(SpringRunner.class)
class ConsultantRepositoryTest {

	@Autowired
	private ConsultantRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void firstLearningTest() {
		Consultant consultant = new Consultant(null, "test", "");
		Consultant saved = repository.save(consultant);
		Collection<Consultant> consultants = repository.findAll();
		assertThat(consultants).containsExactly(saved);
	}

	@Test
	public void secondLearningTest() {

		Consultant consultant = new Consultant(null, "test", "");
		Consultant saved = entityManager.persistFlushFind(consultant);
		Collection<Consultant> consultants = repository.findAll();
		assertThat(consultants).containsExactly(saved);
	}

	@Test
	public void test_findByConsultantName() {
		Consultant saved = entityManager.
			persistFlushFind(new Consultant(null, "test", ""));
		Consultant found = repository.findByFirstName("test");
		assertThat(found).isEqualTo(saved);
	}

}
