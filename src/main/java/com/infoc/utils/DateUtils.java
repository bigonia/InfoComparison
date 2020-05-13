package com.infoc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 	date转换String
	 * @param date
	 * @return String
	 */
	public  String date2String(Date date) {
		return sdf.format(date);
	}
	
	/**
	 * 	时间戳转换String
	 * @param timestamp
	 * @return
	 */
	public  String timestamp2String(String timestamp) {
		return sdf.format(new Date(Long.valueOf(timestamp)));
	}
	
}
