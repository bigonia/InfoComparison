package com.infoc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import net.sf.json.JSONObject;
/**
 * 	抓取源数据
 * @author Administrator
 *
 */
public class DataSource {
//	private final 
//	private  String jcwurl = "http://www.cninfo.com.cn/new/index/getAnnouces?type=sh";
//	private  String sjsurl = "http://www.sse.com.cn/disclosure/listedinfo/bulletin/s_docdatesort_desc_2019openpdf.htm";
	
	/**
	 * 	通过流获取json的初始数据
	 * 	
	 * @param url
	 * @return JSONObject
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public JSONObject getJsonData(String url) throws MalformedURLException, IOException {
		//读取网站数据
		InputStream is = new URL(url).openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));  
        StringBuilder sb = new StringBuilder();  
        //写入
        int cp;  
        while ((cp = br.read()) != -1) {  
          sb.append((char) cp);  
        }  
        //得到json
        String jsonText = sb.toString();  
		
        return JSONObject.fromObject(jsonText);
        
//		return JSONObject.fromObject(Jsoup.connect(url).get());
		
	}
	
	/**
	 * 	使用Jsoup直接获得Document
	 * @param url
	 * @return	Document
	 * @throws IOException
	 */
	public Document getHtmlData(String url) throws IOException {
		
		return Jsoup.connect(url).get();
		
	}
	
	/**
	 * 	统一从两个网站抓取数据的方法,抓取结果转换为String返回;
	 * 	tip：1使用String实例化document
	 * 		 2使用String实例化JSONObject
	 * @param url
	 * @return	String
	 * @throws IOException
	 */
	public String getStringData(String url) throws IOException {
		
//		String JCWurl = "http://www.cninfo.com.cn/new/index/getAnnouces?type=sh";
//		String SJSurl = "http://www.sse.com.cn/disclosure/listedinfo/bulletin/s_docdatesort_desc_2019openpdf.htm";
		//字符输入流
		InputStream is = null;
		//转换流
		InputStreamReader isr = null;
		//缓冲字符输入流
		BufferedReader br = null;
		
		is = new URL(url).openStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		
		StringBuffer sb = new StringBuffer();
		int cp;
		while((cp = br.read()) != -1) {
			sb.append((char)cp);
		}
		//测试SJS
//		String str = sb.toString();
//		System.out.println(str);
//		new TestSJSh().SJSTest(str);
		
		return sb.toString();
		
	}
	
	
}
