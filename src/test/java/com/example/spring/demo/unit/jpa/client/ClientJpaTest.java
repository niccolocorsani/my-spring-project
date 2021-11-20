package com.example.spring.demo.unit.jpa.client;

import com.example.spring.demo.model.client.Client;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@RunWith(SpringRunner.class)
 class ClientJpaTest {

	@Autowired
	private TestEntityManager entityManager;

	@Test
	 void testJpaMapping() {
		Client saved =
			entityManager.
				persistFlushFind(new Client(null, "test", ""));
		assertThat(saved.getFirstName()).isEqualTo("test");
		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getId()).isPositive();
		
	}
}