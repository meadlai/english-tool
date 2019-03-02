package com.meadvoa.main;

public class Main {

	public static void main(String[] args) throws Exception {
		test();
//		HTMLExtract.handleList();
	}

	public static void test() throws Exception {
		String url = "http://voa.iyuba.com/audioitem_standard_9151.html";
		String pureEn = HTMLExtract.handleDetail(url, 0, 1);
		System.out.println("pureEn is ");
		System.out.println(pureEn);
		System.out.println("");
		
		String pureCh = HTMLExtract.handleDetail(url, 1, 1);
		System.out.println("pureCh is ");
		System.out.println(pureCh);
		System.out.println("");
		
		String All = HTMLExtract.handleDetail(url, 2, 1);
		System.out.println("All is ");
		System.out.println(All);
		System.out.println("");
	}
}
