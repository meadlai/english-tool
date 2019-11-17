package com.kmboot.english.service.imp;

import java.util.Date;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NewServiceImp {

	@Autowired
	private DigestService digestService;

	// rateHours = 1 * ((1000 * 60) * 60);
	// 1 hour
	@Scheduled(fixedRate = 1 * (1000 * 60 * 60))
	public void scheduleFixedDelayTask() {
		log.info("Fixed delay task - " + 60 * 3);
		// IntStream.range(0, 5).forEach(digestService::execute);
	}

	// 5 min
	@Scheduled(fixedRate = 5 * (1000 * 60))
	public void scheduleHealthChecker() {
		log.info("scheduleHealthChecker" + new Date());
		digestService.cleanup();
	}
}
