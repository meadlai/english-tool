package com.kmboot.english.service;

public class EnglishException extends RuntimeException {
	private static final long serialVersionUID = 889797124016659565L;

	public EnglishException() {
		super();
	}

	public EnglishException(String message) {
		super(message);
	}

	public EnglishException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnglishException(Throwable cause) {
		super(cause);
	}
	
}


