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
import com.kmboot.english.entity.Channel;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(classes = EnglishApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("local")
//@Import({ TestConfig.class })
//@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
//@Transactional
@Slf4j
public class ChannelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ChannelRepository repository;


	@Test
	@Rollback(false)
	public void testAllInOne() {
		log.info("testAllInOne");
		Channel entity = new Channel();
		entity.setChannelName("channelName");
		this.repository.save(entity);
	}

}
