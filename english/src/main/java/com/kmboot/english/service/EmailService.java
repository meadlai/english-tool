package com.kmboot.english.service;

import java.util.List;

public interface EmailService {

	public void sendSimpleMessage(String to, String title, String message);

	public void sendSimpleMessage(String to, String title, List<String> bcc, String message);
}
