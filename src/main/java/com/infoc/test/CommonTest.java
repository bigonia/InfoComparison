package com.infoc.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class CommonTest {
	public static void main(String[] args) throws MalformedURLException, IOException {
		String url = "http://www.cninfo.com.cn/new/index/getAnnouces?type=sh";
		String path = "src/main/resources/data.txt";
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		File file = null;
//		FileInputStream fis = null;
		FileWriter fr = null;
//		new URL(url).openConnection();
		is = new URL(url).openStream();
		isr = new InputStreamReader(is);
		file = new File(path);
		if(!file.exists()) file.createNewFile();
		System.out.println("fileExist:"+file.exists());
//		fis = new FileInputStream(file);
		fr = new FileWriter(file);
		br = new BufferedReader(isr);
		
//		String str = null;
//		while((str = br.readLine()) != null) {
//			fr.write(str);
//		}
		StringBuffer sb = new StringBuffer();
		int cp;
		while((cp = br.read()) != -1) {
			sb.append((char)cp);
//			fr.write((char)cp);
		}
		fr.write(sb.toString());
		fr.flush();
		System.out.println(sb.toString());
		
	}
}
