package com.infoc.main;

import java.io.IOException;
import java.util.List;

import com.infoc.data.DataSource;
import com.infoc.data.DataTransform;
import com.infoc.entity.Announcement;
import com.infoc.utils.Config;

import net.sf.json.JSONObject;

public class Demo {
	//数据源
	public DataSource dataSource = new DataSource();
	public DataTransform dataT = new DataTransform();
	//配置类
	public Config config = new Config();
	//需要的数据
	public String jcwurl = config.JCW_URL_POST;
	public String content;
	//分页查询数据
	int beginnum = 1;
	
	
	public void run() throws IOException {
		boolean hasNext = true;
		while(hasNext!=false){	
			content = config.getContent(beginnum);
			JSONObject jsonDataByPost = dataSource.getJsonDataByPost(jcwurl, content);
			hasNext = dataT.json2AnnForPost(jsonDataByPost);
			beginnum++;
			if(beginnum%5==0) {
				System.out.println(beginnum+"||"
						+content+"||"
						+dataT.getJcwAnn().size()
						);
			}
		};
		
		List<Announcement> jcwAnn = dataT.getJcwAnn();
		System.out.println("jcw.size:"+jcwAnn.size());
	}
	
	public static void main(String[] args) {
		try {
			new Demo().run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
