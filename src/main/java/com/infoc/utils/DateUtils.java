package com.infoc.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
	/**
	 * 	date转换String
	 * @param date
	 * @return String
	 */
	public static String date2String(Date date) {
		return sdf.format(date);
	}
	
	/**
	 * 	时间戳转换String
	 * @param timestamp
	 * @return
	 */
	public static String timestamp2String(String timestamp) {
		return sdf.format(new Date(Long.valueOf("timestamp")));
	}
	
}
