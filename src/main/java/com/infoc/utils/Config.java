package com.infoc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 	配置类：用于配置项目的各项属性；利用propoties读取配置信息；
 * @author Administrator
 *
 */
public class Config {
	
	//巨潮网数据的url
	public  static String JCW_URL = "http://www.cninfo.com.cn/new/index/getAnnouces?type=sh";
	//上交所数据的url
	public  static String SJS_URL = "http://www.sse.com.cn/disclosure/listedinfo/bulletin/s_docdatesort_desc_2019openpdf.htm";
	//propoties
	private static Properties properties = new Properties();
	
	//静态代码块；类加载时读取各项配置信息并相应赋值
	static {
		//目前需要配置的内容：两个网站url、发送的邮件地址、...
		
		//读取propoties /infoComparison/src/main/resources/config.properties
		InputStream in = ClassLoader
                .getSystemResourceAsStream("src/main/resources/config.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void listPropoties() {
		for (String key : properties.stringPropertyNames()) {
			System.out.println(key + "=" + properties.getProperty(key));
		}
	}
	
	public static void main(String[] args) {
		new Config().listPropoties();
	}
	
}
