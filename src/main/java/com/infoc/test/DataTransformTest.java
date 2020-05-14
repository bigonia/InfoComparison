package com.infoc.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.infoc.controller.DataSource;
import com.infoc.controller.DataTransform;
import com.infoc.entity.Announcement;
import com.infoc.utils.Config;

import net.sf.json.JSONObject;
/**
 * 	测试dataTransform类
 * @author Administrator
 *
 */
public class DataTransformTest {
	public static void main(String[] args) throws Exception {
		DataSource dataSource = new DataSource();
		DataTransform dataTransform = new DataTransform();
		
//		JSONObject jsonData = dataSource.getJsonDataByGet(Config.JCW_URL);
		JSONObject jsonData = dataSource
				.getJsonDataByPost(Config.JCW_URL_POST,Config.POST_CONTENT);
//		dataTransform.json2AnnForGet(jsonData);
		dataTransform.json2AnnForPost(jsonData);
		List<Announcement> jcwAnn = dataTransform.getJcwAnn();
		System.out.println(jcwAnn.size());
		
		
		
	}
	
}
