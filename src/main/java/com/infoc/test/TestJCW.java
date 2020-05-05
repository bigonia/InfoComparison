package com.infoc.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TestJCW {
	public static void main(String[] args) throws IOException {
		String url = "http://www.cninfo.com.cn/new/index/getAnnouces?type=sh";
		Document document = Jsoup.connect(url).get();
		//返回值无法处理
		String html = document.html();
		System.out.println(html);
	}
}
