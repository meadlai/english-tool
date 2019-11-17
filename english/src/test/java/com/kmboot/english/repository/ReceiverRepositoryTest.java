package com.kmboot.english.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import com.kmboot.english.EnglishApplication;
import com.kmboot.english.entity.Receiver;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnglishApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class ReceiverRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ReceiverRepository repository;
	
	
	@Test
	@Rollback(false)
	public void testAllInOne() {
		log.info("testAllInOne");
		Receiver entity = new Receiver();
		entity.setEmailAddress("meadlai@qq.com");
		entity.setAvailable(true);
		entity.setEmailCode("random");
		entity.setEmailValid(true);
		entity.setUserName("username");
		this.repository.save(entity);
	}

	
}
