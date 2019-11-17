package com.kmboot.english.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.kmboot.english.EnglishApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnglishApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class EmailServiceTest {

	@Autowired
	private EmailService email;

	@Test
	public void testEmail() {
		String to = "meadlai@163.com";
		String message = "Spring Test";
		email.sendSimpleMessage(to, "TITLE", message);
	}

}
