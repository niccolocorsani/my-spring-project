package com.example.spring.demo.unit.repositories.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.repositories.client.ClientRepository;


@DataJpaTest
@RunWith(SpringRunner.class)
public class ClientRepositoryTest {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void firstLearningTest() {
		Client client = new Client(null, "test", "");
		Client saved = repository.save(client);
		Collection<Client> clients = repository.findAll();
		assertThat(clients).containsExactly(saved);
	}

	@Test
	public void secondLearningTest() {
		// we can add a record with the TestEntityManager
		// and read it back with the Repository
		Client client = new Client(null, "test", "");
		Client saved = entityManager.persistFlushFind(client);
		Collection<Client> clients = repository.findAll();
		assertThat(clients).containsExactly(saved);
	}

	@Test
	public void test_findByClientName() {
		Client saved = entityManager.
			persistFlushFind(new Client(null, "test", ""));
		Client found = repository.findByFirstName("test");
		assertThat(found).isEqualTo(saved);
	}

}