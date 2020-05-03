package com.infoc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.sf.json.JSONObject;

public class DataSource {
//	private final 
	private String jcwurl = "http://www.cninfo.com.cn/new/index/getAnnouces?type=sh";
	
	//获取json数据
	public JSONObject JsonData(String url) throws MalformedURLException, IOException {
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
		
	}
	
	//获取html数据
	public Document HtmlData(String url) throws IOException {
		
		//http://www.sse.com.cn/disclosure/listedinfo/bulletin/s_docdatesort_desc_2019openpdf.htm
//		Document document = Jsoup.connect(url).get();
		
		return Jsoup.connect(url).get();
		
	}
	
}
