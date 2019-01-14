package com.amcloth.word;

public class AnalyseBook {

	public static void main(String[] args) {
		WordList w4 = new WordList("CET4", "CET_4_pure.txt");
		ReportData r4 = new ReportData(w4);
		PDFReader pr4 = new PDFReader("jobs.pdf", r4);
		pr4.execute();
		r4.print();
		
//		WordList w6 = new WordList("CET6", "CET_6_pure.txt");
//		ReportData r6 = new ReportData(w6);
//		PDFReader pr = new PDFReader("jobs.pdf", r6);
//		pr.execute();
//		r6.print();

	}

}
