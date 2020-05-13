package com.infoc.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	public void json2Ann (JSONObject data) {
		
		//json数据中totalAnnouncement的值
		int totalAnnouncement =  (Integer) data.get("totalAnnouncement");
		//计算公告数量
		int AnnNum = 0;
		
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
