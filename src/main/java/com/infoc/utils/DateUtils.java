package com.infoc.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 	日期配置类
 * @author Administrator
 *
 */
public class DateUtils {
	
	private  SimpleDateFormat yMd = new SimpleDateFormat("yyyy-MM-dd");
	private  SimpleDateFormat Md = new SimpleDateFormat("MM-dd");
	private  SimpleDateFormat Hm = new SimpleDateFormat("HHmm");
	/**
	 * 	date转换String
	 * @param date
	 * @return String
	 */
	public  String date2String(Date date) {
		return yMd.format(date);
	}
	
	/**
	 * 	时间戳转换String
	 * @param timestamp
	 * @return
	 */
	public  String timestamp2String(String timestamp) {
		return yMd.format(new Date(Long.valueOf(timestamp)));
	}
	
	/**
	 * 	得到最新日期，即第二天的日期 yyyy-MM-dd
	 * @return
	 */
	public String getNextyMd() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		return yMd.format(calendar.getTime());
		
	}
	
	/**
	 * 	得到最新日期，即第二天的日期MM-dd
	 * @return
	 */
	public String getNextMd() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		return Md.format(calendar.getTime());
		
	}
	
	/**
	 * 	得到HHmm格式的当前时间
	 * @return
	 */
	public String getHm() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		return Hm.format(calendar.getTime());
		
	}
	
	/**
	 * 	得到post参数需要的时间参数setData
	 * @return
	 */
	public String getSetData() {
		String nextDay = getNextMd();
		return nextDay+"~"+nextDay;
	}
}
