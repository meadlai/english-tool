package com.amcloth.word;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Utils {
	public static <K, V extends Comparable<? super V>> List<Entry<K, V>> sort(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		return list;
	}

//	Stream<Map.Entry<String, Integer>> sorted = map.entrySet().stream()
//	.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

	public static <K, V extends Comparable<? super V>> List<Entry<K, V>> sort2(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return -o1.getValue().compareTo(o2.getValue());
			}
		});

		return list;
	}

	public static String formatPage(int page) {
		int length = ("" + Constant.MAX_PAGE).length();
		String p = String.format("%1$" + length + "s", "" + page);
		return p;
	}

	public static String fixLength(String word) {
		String p = String.format("%1$" + Constant.MAX_WORD + "s", word);
		return p;
	}

	public static void main(String[] args) {
		int p1 = 1;
		System.out.println("pageNumber: " + formatPage(p1));
		System.out.println("pageNumber: " + formatPage(200));
		System.out.println("pageNumber: " + formatPage(13));
		System.out.println("pageNumber: " + formatPage(1580));

		System.out.println("fixLength: " + fixLength("Mead"));
		System.out.println("fixLength: " + fixLength("test"));
		System.out.println("fixLength: " + fixLength("child"));
		System.out.println("fixLength: " + fixLength("guest"));

		HashMap<String, Integer> ph = new HashMap<String, Integer>();
		ph.put("war", 1);
		ph.put("war", 1);
		ph.put("me", 1);
		ph.put("find",2);
		ph.put("mead", 3);
		ph.put("war", 4);
		
	}
}
