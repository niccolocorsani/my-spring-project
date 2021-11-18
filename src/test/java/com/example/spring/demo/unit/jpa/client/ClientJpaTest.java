package com.example.spring.demo.unit.jpa.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.demo.model.client.Client;


@DataJpaTest
@RunWith(SpringRunner.class)
public class ClientJpaTest {

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testJpaMapping() {
		Client saved =
			entityManager.
				persistFlushFind(new Client(null, "test", ""));
		assertThat(saved.getFirstName()).isEqualTo("test");
		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getId()).isPositive();
		
	}
}