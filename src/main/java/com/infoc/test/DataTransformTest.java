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

public class DataTransformTest {
	public static void main(String[] args) throws Exception {
		DataSource dataSource = new DataSource();
		DataTransform dataTransform = new DataTransform();
		
		JSONObject jsonData = dataSource.getJsonData(Config.JCW_URL);
		dataTransform.json2Ann(jsonData);
		List<Announcement> jcwAnn = dataTransform.getJcwAnn();
		System.out.println(jcwAnn.size());
		
		
		
	}
	
}
