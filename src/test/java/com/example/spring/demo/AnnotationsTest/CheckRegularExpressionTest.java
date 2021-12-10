package com.example.spring.demo.AnnotationsTest;

import com.example.spring.demo.model.client.Client;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CheckRegularExpressionTest {

	@Autowired
	private TestEntityManager entityManager;

	@Test
	void testGoodFirstName() {
		Client saved = entityManager.persist(new Client(null, "test", ""));
		assertThat(saved.getFirstName()).isEqualTo("test");
		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getId()).isPositive();
	}

	@Test
	void testBadFirstName() {
		assertThrows(ConstraintViolationException.class, () -> {
			entityManager.persist(new Client(null, "test!1=@", ""));
		});
	}
}

