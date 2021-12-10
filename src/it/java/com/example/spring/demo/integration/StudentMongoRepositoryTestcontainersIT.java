package com.example.spring.demo.integration;

import org.junit.ClassRule;
import org.testcontainers.containers.GenericContainer;

public class StudentMongoRepositoryTestcontainersIT {
	
	
	@SuppressWarnings("rawtypes")
	
	@ClassRule
	public static final GenericContainer mySQL =
	new GenericContainer("mongo:4.0.5")
	.withExposedPorts(27017);

	

	
	
}
