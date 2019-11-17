package com.kmboot.english.service.imp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.kmboot.english.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailServiceImp implements EmailService {

	@Value("${english.email.sender:meadlai@qq.com}")
	public String FROM;

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendSimpleMessage(String to, String title, String text) {
		this.sendSimpleMessage(to, title, null, text);
	}

	@Override
	public void sendSimpleMessage(String to, String title, List<String> bcc, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(FROM);
		message.setSubject(this.getSubject() + title);
		message.setText(text);
		//
		if (bcc != null) {
			message.setBcc(bcc.stream().toArray(String[]::new));
		}
		//
		emailSender.send(message);
		log.info("sending email to {}", to);

	}

	private static int count = 0;
	private static String today = "";
	private static final String PREFIX = "VOA ";

	/**
	 * VOA_Text on 2019-11-11: 1
	 * 
	 * @return
	 */
	private String getSubject() {
		LocalDate now = LocalDate.now();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String todayString = fmt.format(now);
		if (todayString.equalsIgnoreCase(today)) {
			count++;
		} else {
			count = 1;
			today = todayString;
		}
		todayString = PREFIX + todayString + " #" + count + ": ";
		return todayString;
	}
}
