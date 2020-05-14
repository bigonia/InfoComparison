package com.infoc.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.infoc.entity.AnnSource;
import com.infoc.entity.Announcement;
import com.infoc.utils.DateUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 	数据格式处理和转换
 * @author Administrator
 *
 */
public class DataTransform {
	
	private List<Announcement> jsjAnn = new LinkedList<Announcement>();
	private List<Announcement> jcwAnn = new LinkedList<Announcement>();
	
	DateUtils dateUtils = new DateUtils();
	
	/**
	 * 	将json数据实例化
	 * @param data
	 */
	public void json2AnnForGet (JSONObject data) {
		
		//json数据中totalAnnouncement的值
		int totalAnnouncement =  (Integer) data.get("totalAnnouncement");
		//计算公告数量
		int AnnNum = 0;
		
		//将所以公告json放入集合
		LinkedList<JSONObject> jsonList = new LinkedList<JSONObject>();
		
		//得到根据公司名封装的json数据
		JSONArray classifiedAnnouncementsDatas = (JSONArray) data.get("classifiedAnnouncements");
		
		for (Object classifiedAnnouncementsData : classifiedAnnouncementsDatas) {
			JSONArray classifiedAnnouncements_index = (JSONArray) classifiedAnnouncementsData;
			//得到公告的json数据
			for (Object classifiedAnnouncements_index_index : classifiedAnnouncements_index) {
				JSONObject AnnJson = (JSONObject) classifiedAnnouncements_index_index;
				jsonList.add(AnnJson);
			}
		}
		
		AnnNum = jsonList.size();
		
		System.out.println("AnnNum:"+AnnNum);
		System.out.println("totalAnnouncement:"+totalAnnouncement);
		
		for (JSONObject AnnJson : jsonList) {
			int code = Integer.parseInt((String) AnnJson.get("secCode")) ;
			String title = (String) AnnJson.get("announcementTitle");
			String timestamp = String.valueOf(AnnJson.get("announcementTime"));
			String time = dateUtils.timestamp2String(timestamp);
			Announcement ann = new Announcement(code, title, time ,AnnSource.JCW);
			jcwAnn.add(ann);
		}
		
	}
	
	/**
	 * 	处理json数据，因为需要多次查询，所以这个方法要被调用多次
	 * @param data
	 */
	public void json2AnnForPost(JSONObject data) {
		
		//得到json数据中的总公告数量
		int totalAnnouncement = (Integer) data.get("totalAnnouncement");
		System.out.println("totalAnnouncement:"+totalAnnouncement);
		//公告   
		JSONArray announcements = (JSONArray) data.get("announcements");
//		JSONObject announcements = (JSONObject) data.get("announcements");
//		JSONArray announcementsArr = JSONArray.fromObject(announcements);
//		java.lang.ClassCastException: net.sf.json.JSONObject 
//			cannot be cast to net.sf.json.JSONArray
		//announcements集合中即为公告信息
		for (Object object : announcements) {
			JSONObject json = JSONObject.fromObject(object);
			int code = Integer.parseInt((String) json.get("secCode")) ;
			String title = (String) json.get("announcementTitle");
			String timestamp = String.valueOf(json.get("announcementTime"));
			String time = dateUtils.timestamp2String(timestamp);
			Announcement ann = new Announcement(code, title, time ,AnnSource.JCW);
			jcwAnn.add(ann);
		}
		
	}
	
	public void Document2Ann(Document document) {
		//获取tag div
        Elements divs = document.getElementsByTag("div");
        System.out.println("获取div标签;div.size:"+divs.size());
        Element div = divs.get(0);
        //打印div
        //System.out.println("printdiv:"+div.html());
        //得到div,获取dl
        Elements dls = div.getElementsByTag("dl");
        System.out.println("获取dl标签;dls.size:"+dls.size());
        Element dl = dls.get(0);
        //得到dl，获取dd
        Elements dds = dl.getElementsByTag("dd");
        System.out.println("获取dd标签;dds.size:"+dds.size());
        //得到dd，获取所需数据
        Element dd1 = dds.get(0);
        Elements ems = dd1.getElementsByTag("em");
        Element em = ems.get(0);
        Elements as = em.getElementsByTag("a");
        Element a = as.get(0);
        //val为input数据
//		        String val = a.val();
//		        System.out.println("val:"+val);
        System.out.println("打印a标签:"+a.html());
	}
	
	public List<Announcement> getJsjAnn() {
		return jsjAnn;
	}
	public void setJsjAnn(List<Announcement> jsjAnn) {
		this.jsjAnn = jsjAnn;
	}
	public List<Announcement> getJcwAnn() {
		return jcwAnn;
	}
	public void setJcwAnn(List<Announcement> jcwAnn) {
		this.jcwAnn = jcwAnn;
	}
	 
	 
	 
	
}
