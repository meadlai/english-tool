package com.meadvoa.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLExtract {
	public static final int Lang_EN = 0;
	public static final int Lang_ZH = 1;
	public static final int Lang_ALL = 2;
	private static final String sep = "\n";
	private static final String url_Main = "http://voa.iyuba.com/voachangsu.html";

	public static List<Item> handleList() throws Exception {
		List<Item> list = new ArrayList<Item>();
		Document doc = Jsoup.connect(url_Main).get();
		Elements content = doc.select("a"); 
		

		return list;
	}

	public static String handleDetail(String url, int lang, int mark) throws Exception {
		StringBuilder result = new StringBuilder();
		Document doc = Jsoup.connect(url).get();
		Elements content = null;
		Elements content2 = null;

		switch (lang) {
		case 0:
			content = doc.select(".initEnX");
			break;
		case 1:
			content = doc.select(".initCnX");
			break;
		case 2:
			content = doc.select(".initEnX");
			content2 = doc.select(".initCnX");
			break;
		default:
			content = doc.select(".initEnX");
			break;

		}
		if (content == null) {
			return "";
		}

		for (int i = 0; i < content.size() - 1; i++) {
			Element e = content.get(i);
			result.append(e.text());
			result.append(sep);
		}
		if (content2 != null) {
			for (int i = 0; i < content2.size() - 1; i++) {
				Element e = content2.get(i);
				result.append(e.text());
				result.append(sep);
			}
		}

		return result.toString();
	}
}
