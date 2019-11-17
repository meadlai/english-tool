package com.kmboot.english.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadUtils {
	public static final int WAIT = 1000;

	public static void sleep() {
		try {
			Thread.sleep(WAIT);
		} catch (InterruptedException e) {
			log.error("Got InterruptedException on sleep", e);
		}
	}

	public static void sleep(int seconds) {
		try {
			Thread.sleep(WAIT * seconds);
		} catch (InterruptedException e) {
			log.error("Got InterruptedException on sleep", e);
		}
	}
}
