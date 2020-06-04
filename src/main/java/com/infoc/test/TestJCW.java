package com.infoc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import com.infoc.data.DataSource;

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
	
	public  static String JCW_URL_POST = "http://www.cninfo.com.cn/new/hisAnnouncement/query";
	//巨潮网的post_url的content
	public	static String POST_CONTENT = "pageNum=1&seDate=2020-05-14~2020-05-14&pageSize=30&column=szse&tabName=fulltext&plate=sh&stock=&searchkey=&secid=&category=&trade=&sortName=&sortType=&isHLtitle=true";
	public	static String POST_CONTENT_2 = "pageNum=2&seDate=2020-05-14~2020-05-14&pageSize=30&column=szse&tabName=fulltext&plate=sh&stock=&searchkey=&secid=&category=&trade=&sortName=&sortType=&isHLtitle=true";
	public	static String POST_CONTENT_3 = "pageNum=3&seDate=2020-05-14~2020-05-14&pageSize=30&column=szse&tabName=fulltext&plate=sh&stock=&searchkey=&secid=&category=&trade=&sortName=&sortType=&isHLtitle=true";
	
	/**
	 * 565
	 * pageNum=1&pageSize=30&column=szse&tabName=fulltext&plate=sh&stock=&searchkey=&secid=&category=&trade=&seDate=2020-05-14~2020-05-14&sortName=&sortType=&isHLtitle=true
	 * 696029
	 * pageNum=10&pageSize=30&column=szse&tabName=fulltext&plate=sh&stock=&searchkey=&secid=&category=&trade=&seDate=2020-05-14~2020-05-14&sortName=&sortType=&isHLtitle=true
	 * 
	 * 
	 */
	
	
	public	static String POST_CONTENT_n = "pageNum=1&seDate=2020-06-02~2020-06-02&pageSize=30&column=szse&tabName=fulltext&plate=sh&stock=&searchkey=&secid=&category=&trade=&sortName=&sortType=&isHLtitle=true";
	
	/**
	 * 		如果输入data数据，则使用data数据生成json用相同的逻辑进行处理，
	 *	 若data为null；使用从网站抓取的数据，以此对比。
	 * 	
	 * @param data
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public  void JCWTestForGet(String data) throws MalformedURLException, IOException {
		
		
		//得到原始数据
        String jsonText = new DataSource().getStringData(jcwurl);  
        //判断是否传入检测数据
        if(data!=null) {
        	jsonText = data;
        	System.out.println("生成JSON，测试传入的String");
        };
        //得到json数据
        //使用JSONObject解析处理
        JSONObject json = JSONObject.fromObject(jsonText); 
        System.out.println("json.size():"+json.size());
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
	/**
	 * 	同上,测试post返回的json格式
	 * @param data
	 * @throws IOException 
	 */
	public	void JCWTestForPost(String data) throws IOException {
		
		JSONObject source = new DataSource()
				.getJsonDataByPost(JCW_URL_POST	,POST_CONTENT);
		
		//是否传入数据
		if(data!=null) {
			source = JSONObject.fromObject(data);
			System.out.println("生成json，测试传入的数据");
		}
		
		System.out.println("json.size:"+source.size());
		int totalAnnouncement = (Integer) source.get("totalAnnouncement");
		System.out.println("totalAnnouncement:"+totalAnnouncement);
		JSONArray announcements = (JSONArray) source.get("announcements");
		System.out.println("announcements.size:"+announcements.size());
		JSONObject announcements_index = (JSONObject) announcements.get(0);
//		System.out.println("announcements_index.size:"+announcements_index.size());
		String secName = (String) announcements_index.get("secName");
		System.out.println("secName:"+secName);
		String announcementTitle = (String) announcements_index.get("announcementTitle");
		System.out.println("announcementTitle:"+announcementTitle);
		Boolean hasMore = (Boolean) source.get("hasMore");
		System.out.println("hasMore:"+hasMore);
		
	}
	
	public static void main(String[] args) {
		try {
//			new TestJCW().JCWTestForGet(null);
			new TestJCW().JCWTestForPost(null);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
