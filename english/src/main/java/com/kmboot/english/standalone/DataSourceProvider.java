package com.kmboot.english.standalone;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceProvider {

	@Bean
	@ConfigurationProperties("spring.datasource")
	@ConditionalOnMissingBean
	public DataSource defaultDataSource() {
		return DataSourceBuilder.create().build();
	}

	
}
