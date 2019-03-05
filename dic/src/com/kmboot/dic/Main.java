package com.kmboot.dic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.kmboot.ws.ArrayOfDefinition;
import com.kmboot.ws.Definition;
import com.kmboot.ws.DictService;
import com.kmboot.ws.DictServiceSoap;
import com.kmboot.ws.Dictionary;
import com.kmboot.ws.WordDefinition;

public class Main {

	private static String detail_path = "";
	private static String dic_path = "";
	private static String properties_path = "";
	/**
	 * wsimport -keep -p com.kmboot.ws http://localhost:9999/ws/hello?wsdl
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception {
		Properties prop = new Properties();
		try {
			File jarPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			properties_path = jarPath.getParentFile().getAbsolutePath();
			String propertiesFileName = properties_path + "/dic.properties";
			// set the default log files.
			detail_path = properties_path + "/detail.txt";
			dic_path = properties_path + "/dic.txt";
			// set the file paths from setting file: dic.properties
			if(new File(propertiesFileName).exists() == true){
				prop.load(new FileInputStream(propertiesFileName));
				if (StringUtils.isNotBlank(prop.getProperty("proxyHost"))) {
					System.setProperty("http.proxyHost", prop.getProperty("proxyHost"));
				}
				if (StringUtils.isNotBlank(prop.getProperty("proxyPort"))) {
					System.setProperty("http.proxyPort", prop.getProperty("proxyPort"));
				}
				if (StringUtils.isNotBlank(prop.getProperty("detailFile"))) {
					detail_path = prop.getProperty("detailFile");
				}
				if (StringUtils.isNotBlank(prop.getProperty("dicFile"))) {
					dic_path = prop.getProperty("dicFile");
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		String[] list = Objects.requireNonNull(arg, "Argument must not be null");
		if (list.length == 1) {
			lookup(list[0]);
		} else {
			throw new IllegalArgumentException("Null argument");
		}
	}

	private static void lookup(String word) throws Exception {
		DictService ds = new DictService();
		DictServiceSoap soap = ds.getDictServiceSoap();
		WordDefinition wd = soap.define(word);
		ArrayOfDefinition list = wd.getDefinitions();
		List<Definition> dlist = list.getDefinition();
		File detail = new File(detail_path);
		File dic = new File(dic_path);
		// filter location and ZIP
		for (Definition d : dlist) {
			String def = d.getWordDefinition().trim();
			if(def.contains("Location:")){
				continue;
			}
			if(def.contains("Zip code(s):")){
				continue;
			}
			System.out.println(def);
			FileUtils.writeStringToFile(detail, d.getWordDefinition(), Charset.defaultCharset(), true);
		}
		if (dlist.size() > 0) {

			FileUtils.writeStringToFile(dic, "#" + word + "\n", Charset.defaultCharset(), true);
		}
	}

	private static void diclist() throws Exception {
		DictService dic = new DictService();
		DictServiceSoap soap = dic.getDictServiceSoap();
		List<Dictionary> dlist = soap.dictionaryList().getDictionary();
		for (Dictionary d : dlist) {
			System.out.println(d.getName() + ", with id: " + d.getId());
		}
	}

}
