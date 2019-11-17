package com.kmboot.english.service.imp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kmboot.english.utils.ThreadUtils;
import com.machinepublishers.jbrowserdriver.JBrowserDriver;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;

@Data
@Slf4j
public class DigestUtil {

	private JBrowserDriver driver;
	
	@Setter(AccessLevel.NONE)
	private String json = "";
	@Setter(AccessLevel.NONE)
	private String url = "";
	@Setter(AccessLevel.NONE)
	private String title = "";

	public void execute(int index) {

		// start the proxy
		BrowserMobProxy proxy = new BrowserMobProxyServer();
		proxy.start(0);

		// get the Selenium proxy object
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

		// configure it as a desired capability
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
		// start the browser up

		// enable more detailed HAR capture, if desired (see CaptureType for the
		// complete list)
		proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

		// create a new HAR with the label "yahoo.com"
		proxy.newHar("m.iyuba.cn");
		proxy.addResponseFilter((response, contents, messageInfo) -> {
			if (messageInfo.getOriginalUrl().contains("getText.jsp")) {
				log.info("## hit url: {}", messageInfo.getUrl());
				json = contents.getTextContents();
				url = messageInfo.getUrl();
			}

		});

//		driver = new JBrowserDriver(Settings.builder().timezone(Timezone.ASIA_SHANGHAI).userAgent(UserAgent.CHROME)
//				.build());

		driver = new JBrowserDriver(capabilities);

		// says 120 but is really 0
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);

		driver.get("http://m.iyuba.cn/");

		// ## goto normal VOA
		List<WebElement> csvoa = driver.findElements(By.xpath("//*[@data-nav='csvoa']"));

		System.out.println("csvoa = " + csvoa.size());
		System.out.println("title = " + title);
		if (csvoa.size() <= 0) {
			System.out.println("not find the csvoa, return...");
			return;
		} else {
			System.out.println("## csvoa.size = " + csvoa.size());
		}

		ThreadUtils.sleep();

		// ## scroll down
		driver.executeScript("scroll(0,700)");

		ThreadUtils.sleep();

		// -- snapshoot_1
		// takeSnapShoot(1);

		// get $csvoa
		WebElement div = csvoa.get(0);
		div.click();

		ThreadUtils.sleep();

		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("list01")));

		// ## click
		List<WebElement> list = driver.findElements(By.className("list01"));
		if (list.size() <= 0) {
			System.out.println("not find the list, return...");
			return;
		} else {
			System.out.println("## list = " + list.size());
		}
		ThreadUtils.sleep();
		// get one item from the list
		WebElement item = list.get(index);

		ThreadUtils.sleep();
		item.click();
		ThreadUtils.sleep();
//		System.out.println("source = " + source);
		title = driver.getTitle();
		log.info("title = " + title);
		url = driver.getCurrentUrl();
		log.info("currentUrl = " + url);
		ThreadUtils.sleep(4);

		try {
			driver.close();
		} catch (Exception e) {
			log.error("Exception on driver.close()", e.getMessage());
			// e.printStackTrace();
		}
		log.info("");
	}

}
