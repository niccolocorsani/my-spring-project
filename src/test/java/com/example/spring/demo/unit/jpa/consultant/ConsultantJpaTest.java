package com.example.spring.demo.unit.jpa.consultant;

import com.example.spring.demo.model.consultant.Consultant;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
 class ConsultantJpaTest {

	@Autowired
	private TestEntityManager entityManager;

	@Test
	 void testJpaMapping() {
		Consultant saved = entityManager.persistFlushFind(new Consultant(null, "test", ""));
		assertThat(saved.getFirstName()).isEqualTo("test");
		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getId()).isPositive();
	}
}