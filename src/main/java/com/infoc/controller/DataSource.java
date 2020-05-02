package com.infoc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import net.sf.json.JSONObject;

public class DataSource {
//	private final 
	private String jcwurl = "http://www.cninfo.com.cn/new/index/getAnnouces?type=sh";
	
	public JSONObject getData(String url) throws MalformedURLException, IOException {
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
	
}
