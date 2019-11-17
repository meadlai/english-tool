package com.kmboot.english.repository;

import javax.persistence.EntityManager;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 *
 */
@Configuration
public class TestConfig {

	@Bean
	public TestEntityManager testEntityManager(EntityManager entityManager) {
		TestEntityManager tem = new TestEntityManager(entityManager.getEntityManagerFactory());
		return tem;
	}
}
