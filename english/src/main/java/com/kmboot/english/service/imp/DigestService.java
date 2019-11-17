package com.kmboot.english.service.imp;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kmboot.english.model.VOANew;
import com.kmboot.english.service.EmailService;
import com.kmboot.english.utils.ThreadUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DigestService {

	private Set<String> ccList = new HashSet<>();
	private static final String[] dateFormats = new String[] { "yyyy-MM-dd HH:mm:ss.s", "yyyy-MM-dd HH:mm:ss" };

	@Autowired
	private EmailService email;

	private static final Map<String, Integer> create2IdMap = new ConcurrentHashMap<>();

	public static void put(String key, Integer id) {
		create2IdMap.put(key, id);
	}

	public static Integer get(String key) {
		return create2IdMap.get(key);
	}

	@Async
	public void cleanup() {
		log.info(" # cleanup...");
		Date today = new Date();
		create2IdMap.entrySet().removeIf(p -> {
			Date d;
			try {
				d = DateUtils.parseDate(p.getKey(), dateFormats);
			} catch (ParseException e) {
				log.info("## Error on date parseing: {}", p.getKey());
				return true;
			}
			boolean result = DateUtils.isSameDay(d, today);
			if (result == false) {
				log.debug("# not same day, remove the item on {} with id: {}", p.getKey(), p.getValue());
				return true;
			} else {
				log.debug("# same day, keep the item on {} with id: {}", p.getKey(), p.getValue());
				return false;
			}
		});
	}

	
	@Async
	public void execute(int index) {
		System.err.println("Create new Thread" + index + ", by thread name: " + Thread.currentThread().getName());
		try {
			Thread.sleep(1000*index);
		} catch (InterruptedException e1) {
		}
		DigestUtil digest = new DigestUtil();
		ThreadUtils.sleep(index);
		try {
			VOANew inew = new VOANew(digest.getUrl(), digest.getTitle(), digest.getJson());
			if (create2IdMap.get(inew.getCreate()) != null) {
				log.info("### The new was sent today with id: {}, title: {}, at: {}",
						create2IdMap.get(inew.getCreate()), inew.getTitle(), inew.getCreate());
				return;
			} else {
				log.info("### sending news with id: {}, title: {}, at: {}", create2IdMap.get(inew.getCreate()),
						inew.getTitle(), inew.getCreate());
				create2IdMap.put(inew.getCreate(), inew.getId());
				this.cleanup();
			}
			//
			String to = "meadlai@163.com";
			email.sendSimpleMessage(to, "EN:" + inew.getTitle(), inew.getTextEN());
			email.sendSimpleMessage(to, "CN:" + inew.getTitle(), inew.getTextCN());
		} catch (Exception e) {
		}
	}
}
