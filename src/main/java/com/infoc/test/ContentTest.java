package com.infoc.test;

import com.infoc.utils.Config;

/**
 * 	测试post请求的参数，该参数自动生成，日期为次日日期
 * @author Administrator
 *
 */
public class ContentTest {
	public static void main(String[] args) {
		Config config = new Config();
		String content = config.getContent(1);
		System.out.println(content);
	}
}

