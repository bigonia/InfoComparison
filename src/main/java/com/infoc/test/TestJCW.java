package com.infoc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 	测试从巨潮网抓取的数据是否符合预期
 * 	update from: 测试数据抓取和处理的内部逻辑
 * @author Administrator
 *
 */
public class TestJCW {
	
	public static String jcwurl = "http://www.cninfo.com.cn/new/index/getAnnouces?type=sh";
	
	/**
	 * 		如果输入data数据，则使用data数据生成json用相同的逻辑进行处理，
	 *	 若data为null；使用从网站抓取的数据，以此对比。
	 * 	
	 * @param data
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public  void JCWTest(String data) throws MalformedURLException, IOException {
		
		InputStream is = new URL("http://www.cninfo.com.cn/new/index/getAnnouces?type=sh").openStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));  
        StringBuilder sb = new StringBuilder();  
        int cp;  
        while ((cp = rd.read()) != -1) {  
          sb.append((char) cp);  
        }  
        //得到原始数据
        String jsonText = sb.toString();  
        //判断是否传入检测数据
        if(data!=null) {
        	jsonText = data;
        	System.out.println("生成JSON，测试传入的String");
        };
        //得到json数据
        //使用JSONObject解析处理
        JSONObject json = JSONObject.fromObject(jsonText); 
        System.out.println("json.size():"+json.size());
//        JSONObject jData = json.getJSONObject("classifiedAnnouncements");
//        System.out.println("jData.size()"+jData.size());
//        json.keys();
        //classifiedAnnouncements
        JSONArray classifiedAnnouncements = (JSONArray) json.get("classifiedAnnouncements");
        System.out.println("classifiedAnnouncements.size:"+classifiedAnnouncements.size());
        //
        JSONArray classifiedAnnouncements0 = classifiedAnnouncements.getJSONArray(0);
        System.out.println("classifiedAnnouncements0.size:"+classifiedAnnouncements0.size());
        //
        JSONObject classifiedAnnouncements00 = classifiedAnnouncements0.getJSONObject(0);
        System.out.println("classifiedAnnouncements00.size:"+classifiedAnnouncements00.size());
        //
        Object title = classifiedAnnouncements00.get("announcementTitle");
        System.out.println("title:"+title.toString());
        
//        Set set = json.keySet();
//        for (Object obj : set) {
//			System.out.println(obj);
//		}
	}
	
	public static void main(String[] args) {
		try {
			new TestJCW().JCWTest(null);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
