package com.infoc.test;

import com.infoc.utils.DateUtils;
/**
 * 	测试DateUtils类
 * @author Administrator
 *
 */
public class DateUtilsTest {
	
	public static void main(String[] args) {
		DateUtils dateUtils = new DateUtils();
		System.out.println(dateUtils.getNextyMd());
		System.out.println(dateUtils.getHm());
		System.out.println(dateUtils.getSetData());
		
		String timestamp = "1595606400000";
		String timestamp2String = dateUtils.timestamp2String(timestamp);
		System.out.println(timestamp2String);
	}
	
}
