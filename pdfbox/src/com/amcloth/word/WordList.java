package com.amcloth.word;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class WordList {
	private List<String> list = new ArrayList<String>();
	private String name = "CET4";

	public WordList(String name, String fileName) {
		this.name = name;
		this.init(fileName);
	}

	private void init(String fileName) {
		InputStream is;
		try {
			is = new FileInputStream(fileName);
			this.list = IOUtils.readLines(is, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> getList() {
		return list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void print() {
		for(String word : this.list) {
			System.out.println(word);
		}
	}
	
	public static void main(String[] args) {
		WordList wl = new WordList("cet4", "CET_4_pure.txt");
		wl.print();
	}
}
