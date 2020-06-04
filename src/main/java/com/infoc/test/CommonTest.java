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

import org.jsoup.nodes.Document;

/**
 * 	此类无用。6.4
 * 	目标：统一从两个网站抓取数据的方法；
 * 	预期：抓取结果转换为String返回或存入本地；
 * 	tip：1使用String实例化document
 * 		 2使用String实例化JSONObject
 * @author Administrator
 *
 */
public class CommonTest {
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		String JCWurl = "http://www.cninfo.com.cn/new/index/getAnnouces?type=sh";
		String SJSurl = "http://www.sse.com.cn/disclosure/listedinfo/bulletin/s_docdatesort_desc_2019openpdf.htm";
		//本地文件夹路径
		//String path = new File("").getAbsolutePath()+"/src/main/resources/data.txt";
		String path = "src/main/resources/data.txt";
//		System.out.println(new File("").getAbsolutePath());
//		System.out.println("====================================");
		//字符输入流
		InputStream is = null;
		//转换流
		InputStreamReader isr = null;
		//缓冲字符输入流
		BufferedReader br = null;
		
		File file = null;
//		FileInputStream fis = null;
		FileWriter fw = null;
//		new URL(url).openConnection();
		is = new URL(SJSurl).openStream();
		isr = new InputStreamReader(is);
		file = new File(path);
		//如文件夹不存在，则创建
		if(!file.exists()) file.createNewFile();
		System.out.println("fileExist:"+file.exists());
		System.out.println("path:"+path);
		System.out.println("filePath:"+file.getAbsolutePath());
//		fis = new FileInputStream(file);
		fw = new FileWriter(file);
		br = new BufferedReader(isr);
		
//		String str = null;
//		while((str = br.readLine()) != null) {
//			fr.write(str);
//		}
		StringBuffer sb = new StringBuffer();
		//写入本地文件夹
		int cp;
		while((cp = br.read()) != -1) {
			sb.append((char)cp);
			fw.write((char)cp);
			fw.flush();
		}
//		System.out.println(sb.toString());
		//测试SJS
		String str = sb.toString();
		new TestSJSh().SJSTest(str);
		
		
	}
}
