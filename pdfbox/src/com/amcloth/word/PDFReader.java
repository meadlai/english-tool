package com.amcloth.word;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFReader {
	private String fileName = "";
	private ReportData report = null;

	public PDFReader(String fname, ReportData rd) {
		this.fileName = fname;
		this.report = rd;
	}

	public void execute() {
		this.read();
	}

	private void read() {
		PDFTextStripper pdfStripper = null;
		PDDocument pdfdocument = null;
		COSDocument cosdocument = null;
		File file = new File(this.fileName);
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");) {
			PDFParser parser = new PDFParser(randomAccessFile);
			parser.parse();
			cosdocument = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdfdocument = new PDDocument(cosdocument);
			int count = pdfdocument.getNumberOfPages();

			if (Constant.BOOK_PAGE_END <= 0) {
				// pass
			} else {
				if (count > Constant.BOOK_PAGE_END) {
					count = Constant.BOOK_PAGE_END;
				}
			}
			//
			System.out.println("¶ÁÈ¡Ò³ÊýÎª£º" + count);
			for (int page = 1; page < count; page++) {
				pdfStripper.setStartPage(page);
				pdfStripper.setEndPage(page);
				String text = pdfStripper.getText(pdfdocument);
				String[] words = text.split("[-\\s]");
				for (String word : words) {
					this.report.hit(word, page);
				}
				// System.out.println(text);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				cosdocument.close();
				pdfdocument.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {

	}
}
