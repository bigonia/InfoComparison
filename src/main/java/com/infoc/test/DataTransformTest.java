package com.infoc.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;

import com.infoc.data.DataSource;
import com.infoc.data.DataTransform;
import com.infoc.entity.Announcement;
import com.infoc.utils.Config;

import net.sf.json.JSONObject;
/**
 * 	测试dataTransform类
 * @author Administrator
 *
 */
public class DataTransformTest {
	
	public DataSource dataSource = new DataSource();
	
	public DataTransform dataTransform = new DataTransform();
	
	public static void main(String[] args) throws Exception {
		DataTransformTest test = new DataTransformTest();
		test.JCWTest();
//		test.SJSTest();
	}
	
	/**
	 * 	测试巨潮网
	 * @throws IOException
	 */
	public void JCWTest() throws IOException {
		
//		JSONObject jsonData = dataSource.getJsonDataByGet(Config.JCW_URL);
		JSONObject jsonData = dataSource
				.getJsonDataByPost(Config.JCW_URL_POST,Config.POST_CONTENT);
//		dataTransform.json2AnnForGet(jsonData);
		dataTransform.json2AnnForPost(jsonData);
		List<Announcement> jcwAnn = dataTransform.getJcwAnn();
		//打印巨潮网本次抓取数据的数量
		System.out.println("jcwAnn.size:"+jcwAnn.size());
		//遍历，打印本次抓取的全部公告
		for (Announcement announcement : jcwAnn) {
			System.out.println(announcement.toString());
		}
	}
	
	/**
	 * 	测试上交所
	 * @throws IOException
	 */
	public void SJSTest() throws IOException {
		Document document = dataSource.getHtmlData(Config.SJS_URL);
		dataTransform.Document2Ann(document);
	}
	
}
