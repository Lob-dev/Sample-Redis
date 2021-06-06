package com.example.redis.domain;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * @since       2021.05.28
 * @author      lob
 * @description TestIntegrationContext
 **********************************************************************************************************************/
@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = TestIntegrationContext.ContainerInitialize.class)
public class TestIntegrationContext {

	@Container
	static GenericContainer redisContainer = new GenericContainer("redis")
			.withExposedPorts(6379);

	static class ContainerInitialize implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			TestPropertyValues.of(
					"spring.redis.port=" + redisContainer.getMappedPort(6379)
			).applyTo(applicationContext.getEnvironment());
		}
	}
}
