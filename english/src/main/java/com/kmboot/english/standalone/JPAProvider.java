package com.kmboot.english.standalone;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com" })
@Slf4j
public class JPAProvider {

	private static final String PERSISTENCE_XML_LOCATION = "classpath:/META-INF/persistence.xml";
	private static final String PERSISTENCE_UNIT_NAME = "persistence_unit_kmboot";

	@Bean
	@ConditionalOnMissingBean(EntityManagerFactory.class)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		log.info("@ConditionalOnMissingBean(EntityManagerFactory.class), persistence uinit is {}",
				PERSISTENCE_UNIT_NAME);
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
		vendorAdapter.setShowSql(true);
		vendorAdapter.setGenerateDdl(false);
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		Properties properties = new Properties();
		properties.setProperty("hibernate.jdbc.fetch_size", "1000");
		bean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(vendorAdapter);
//		bean.setPackagesToScan("com");
		bean.setJpaProperties(properties);
		bean.setPersistenceXmlLocation(PERSISTENCE_XML_LOCATION);
		return bean;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory,DataSource dataSource) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setDataSource(dataSource);
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}
}
