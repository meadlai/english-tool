package com.amcloth.word;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReportData {
	private HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
	private HashMap<String, String> wordLocation = new HashMap<String, String>();
	private HashMap<Integer, Integer> pageHitCount = new HashMap<Integer, Integer>();
	private HashMap<Integer, String> pageHitWord = new HashMap<Integer, String>();

	private WordList wordList = null;
	private static final String NULL = "";
	private int limit = 50;

	public ReportData(WordList wordlist) {
		if (Constant.MAX_CONSOLE_OUTPUT > 0) {
			this.limit = Constant.MAX_CONSOLE_OUTPUT;
		} else {
			this.limit = 50;
		}

		this.wordList = wordlist;

		List<String> list = this.wordList.getList();
		for (String word : list) {
			wordMap.put(word, 0);
			wordLocation.put(word, NULL);
		}
	}

	public void hit(String word, int page) {
		if (this.wordList.getList().contains(word)) {
			// continue
		} else {
			return;
		}

		// word location
		String location = this.wordLocation.get(word);
		String fpage = Utils.formatPage(page);
		if (location.contains(fpage)) {
			return;
		}
		location = location + "," + fpage;
		this.wordLocation.put(word, location);
		// word count
		Integer count = wordMap.get(word);
		count++;
		wordMap.put(word, count);

		// page hit count
		Integer pcount = this.pageHitCount.get(page);
		if (pcount == null) {
			pcount = 0;
		}
		pcount++;
		this.pageHitCount.put(page, pcount);

		// page hit word
		String wdlist = this.pageHitWord.get(page);
		if (wdlist == null) {
			wdlist = "";
		}
		// wdlist = wdlist + Utils.fixLength(word);
		wdlist = wdlist + word + ",";
		this.pageHitWord.put(page, wdlist);
	}

	private float percent = 0;
	private int wordhit = 0;
	private int unhit = 0;
	private List<Map.Entry<String, Integer>> frequency = null;
	private List<Map.Entry<Integer, Integer>> density = null;
	private StringBuffer unhit_word = new StringBuffer();

	private void caculate() {

		Set<String> list = wordMap.keySet();
		int count = 0;
		for (String word : list) {
			count = wordMap.get(word);
			if (count > 0) {
				wordhit++;
			} else {
				unhit++;
				unhit_word.append(word);
				unhit_word.append(", ");
			}
		}
		//
		percent = (float) wordhit * 100 / (float) list.size();
		//
		frequency = Utils.sort2(wordMap);
		//
		density = Utils.sort2(this.pageHitCount);

	}

	public void print() {
		this.caculate();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("== Report 文章包含" + this.wordList.getName() + "词汇个数 = " + wordhit);
		System.out.println("== Report 词汇覆盖率为： " + percent + "%");
		System.out.println("== Report 未覆盖率词汇有" + this.unhit + "个：" + this.unhit_word.toString());
		System.out.println("== Report 词频统计分析报告 - " + limit);
		int temp = 0;
		for (Map.Entry<String, Integer> entry : frequency) {
			if (entry.getValue() > 0) {
				temp++;
				System.out.println(Utils.fixLength(entry.getKey()) + " = " + entry.getValue() + ", 页码列表: "
						+ this.wordLocation.get(entry.getKey()));
			}
			if (temp > limit) {
				break;
			}
		}
		System.out.println("== Report 词汇密度分析报告 - " + limit);

		temp = 0;
		for (Map.Entry<Integer, Integer> entry : density) {
			if (entry.getValue() > 0) {
				temp++;
				System.out.println("		第" + Utils.formatPage(entry.getKey()) + "页有" + entry.getValue()
						+ "个单词, 词汇列表为: " + this.pageHitWord.get(entry.getKey()));
			}
			if (temp > limit) {
				break;
			}
		}

	}

	public static void main(String[] args) {
		WordList list = new WordList("CET4", "CET_4_pure.txt");
		ReportData r = new ReportData(list);
		r.hit("war", 1);
		r.hit("war", 1);
		r.hit("war", 2);
		r.hit("legend", 5);
		r.hit("fix", 5);
		r.hit("fix", 99);

		r.print();

	}
}
