package com.infoc.main;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;

import com.infoc.data.DataSource;
import com.infoc.data.DataTransform;
import com.infoc.entity.Announcement;
import com.infoc.utils.Config;
import com.infoc.utils.FileUtils;

import net.sf.json.JSONObject;

public class Demo {
	//数据源
	public DataSource dataSource = new DataSource();
	public DataTransform dataT = new DataTransform();
	//配置类
	public Config config = new Config();
	public FileUtils fileUtils = new FileUtils();
	//需要的数据
//	public String jcwurl = config.JCW_URL_POST;
	//另一个url；修改，config，url属性，content属性，getcontent方法
	public String jcwurl = "http://www.cninfo.com.cn/new/disclosure";
	
	public String sjsurl = config.SJS_URL;
	public String content;
	//分页查询数据
	int beginnum = 1;
	
	
	public void JCWrun() throws IOException {
		boolean hasNext = true;
		content = config.getContent(beginnum);
//		System.out.println(content);
		while(hasNext!=false&&beginnum<100){	
			content = config.getContent(beginnum);
			JSONObject jsonDataByPost = dataSource.getJsonDataByPost(jcwurl, content);
			hasNext = dataT.json2AnnForPost(jsonDataByPost);
			beginnum++;
//			if(beginnum%5==0) {
//				System.out.println(beginnum+"||"
//						+content+"||"
//						+dataT.getJcwAnn().size()
//						);
//			}
		};
		
		List<Announcement> jcwAnn = dataT.getJcwAnn();
//		for (Announcement announcement : jcwAnn) {
//			System.out.println(announcement);
//		}
		
		fileUtils.saveInfo(jcwAnn);
		
		System.out.println("jcw.size:"+jcwAnn.size());
//		System.out.println(content);
		
	}
	
	public void SJSrun() throws IOException {
		
		Document htmlData = dataSource.getHtmlData(sjsurl);
		dataT.Document2Ann(htmlData);
		List<Announcement> sjsAnn = dataT.getSjsAnn();
		for (Announcement announcement : sjsAnn) {
			System.out.println(announcement);
		}
		System.out.println("sjs.size:"+sjsAnn.size());
	}
	
	public static void main(String[] args) {
		try {
			new Demo().JCWrun();
//			new Demo().SJSrun();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
