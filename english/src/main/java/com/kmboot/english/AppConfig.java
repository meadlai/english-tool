package com.kmboot.english;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableScheduling
@EnableAsync
public class AppConfig {

	private static final int SIZE = 10;

	@Bean
	@Primary
	public TaskExecutor coreTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(SIZE);
		executor.setThreadGroupName("main_pool");
		executor.setThreadNamePrefix("*MP_Thread");
		log.info("### init main thread-pool-size: {}", SIZE);
		return executor;
	}
}
