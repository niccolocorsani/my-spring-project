package com.example.spring.demo.integration;


//@RunWith(SpringRunner.class)
//@DataJpaTest
//@Import(ClientService.class)
 class ClientServiceRepositoryIT {
/*
	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRepository clientRepository;

	@Test
	 void testServiceCanInsertIntoRepository() {
		Client saved = clientService.insertNewClient(new Client(1L, "Marco", "Rossi"));
		assertThat(clientRepository.findById(saved.getId())).isPresent();
	}

	@Test
	 void testServiceCanUpdateRepository() {
		Client saved = clientRepository.save(new Client(1L, "Marco", "Rossi"));
		Client modified = clientService.updateClientById(saved.getId(), new Client(saved.getId(), "modified", ""));
		assertThat(clientRepository.findById(saved.getId())).contains(modified);
	}


	@Test

	 void testServiceDeleteClientByID() {
		Client saved = clientService.insertNewClient(new Client(1L, "Marco", "Rossi"));
		clientService.deleteClientById(1L);
		assertNull(clientService.getClientById(saved.getId()));
	}
	*/
}