package com.infoc.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.transform.Source;

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
	
	private List<Announcement> sjsAnn = new LinkedList<Announcement>();
	private List<Announcement> jcwAnn = new LinkedList<Announcement>();
	
	DateUtils dateUtils = new DateUtils();
	
	/**
	 * 	将巨潮网get请求返回的json数据实例化 ，因为测试时发现通过get返回的数据不是全部，
	 * 		所以这个方法目前毫无用处
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
	 * 	处理巨潮网post请求返回的json数据，
	 * 		因为需要多次查询，所以这个方法要被调用多次
	 * 			返回Boolean类型，hasMore
	 * 		！totalAnnouncement异常
	 * @param data
	 */
	public Boolean json2AnnForPost(JSONObject data) {
		
		//得到json数据中的总公告数量
		int totalAnnouncement = (Integer) data.get("totalAnnouncement");
		//打印totalAnnouncement
		//System.out.println("json2AnnForPost.totalAnnouncement:"+totalAnnouncement);
		//公告   
		JSONArray announcements = (JSONArray) data.get("announcements");
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
		
		Boolean hasMore = (Boolean)data.get("hasMore");
		return hasMore;
		
	}
	
	/**
	 * 	上交所返回的html被解析为document，这里处理document
	 * @param document
	 */
	public void Document2Ann(Document document) {
		
		//获取tag div
        Elements divs = document.getElementsByTag("div");
//        System.out.println("获取div标签;div.size:"+divs.size());
        Element div = divs.get(0);
        //打印div
        //System.out.println("printdiv:"+div.html());
        //得到div,获取dl
        Elements dls = div.getElementsByTag("dl");
//        System.out.println("获取dl标签;dls.size:"+dls.size());
        Element dl = dls.get(0);
        //得到dl，获取dd
        Elements dds = dl.getElementsByTag("dd");
//        System.out.println("获取dd标签;dds.size:"+dds.size());
        //得到dd，获取所需数据
        
        for (Element dd : dds) {
        	//实例化对象
        	Announcement ann = new Announcement();
    		ann.setSource(AnnSource.SJS);
        	//data-time标签,设置时间,
        	Elements datatime = dd.getElementsByAttribute("data-time");
        	Element time = datatime.get(0);
        	//有大量多余代码，需做处理
        	ann.setTime(time.html().substring(6, 16));
        	
        	Elements ems = dd.getElementsByTag("em");
            Element em = ems.get(0);
            Elements as = em.getElementsByTag("a");
            Element a = as.get(0);
            //获取标签内容
            String message = a.html();
            String[] split = message.split("： ");
            String code = split[0];
            String title = split[1];
            ann.setCode(Integer.parseInt(code));
            ann.setTitle(title);
//            System.out.println(code+"="+o);
//            System.out.println(a.html());
//            ann.setTitle(a.html());
            sjsAnn.add(ann);
		}
        
	}
	
	
	public List<Announcement> getSjsAnn() {
		return sjsAnn;
	}
	public void setSjsAnn(List<Announcement> jsjAnn) {
		this.sjsAnn = jsjAnn;
	}
	public List<Announcement> getJcwAnn() {
		return jcwAnn;
	}
	public void setJcwAnn(List<Announcement> jcwAnn) {
		this.jcwAnn = jcwAnn;
	}
	 
	 
	 
	
}
