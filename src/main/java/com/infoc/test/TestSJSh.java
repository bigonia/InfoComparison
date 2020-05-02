package com.infoc.test;

import java.io.IOException;
import java.nio.charset.Charset;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestSJSh {
	public static void main(String[] args) throws IOException {
		//http://www.sse.com.cn/disclosure/listedinfo/bulletin/s_docdatesort_desc_2019openpdf.htm
		String url = "http://www.sse.com.cn/disclosure/listedinfo/bulletin/s_docdatesort_desc_2019openpdf.htm";
		Document document = Jsoup.connect(url).get();
		//打印编码格式
		Charset charset = document.charset();
		System.out.println("charset:"+charset);
		//获取html中的标题
		String title = document.title();
        System.out.println("title :"+title);
        //通过classname获取---失败
//        Elements formElement = document.getElementsByClass("sse_list_1");
//        System.out.println(formElement.html());
        //获取HTML页面中的所有链接
//        Elements links = document.select("div");
//        for (Element link : links){
//            System.out.println("link : "+ link.attr("href"));
//            System.out.println("text :"+ link.text());
//        }
        //获取tag div
        Elements divs = document.getElementsByTag("div");
        System.out.println("div.size:"+divs.size());
        Element div = divs.get(0);
        //打印div
        //System.out.println("printdiv:"+div.html());
        //得到div,获取dl
        Elements dls = div.getElementsByTag("dl");
        System.out.println("dls.size:"+dls.size());
        Element dl = dls.get(0);
        //得到dl，获取dd
        Elements dds = dl.getElementsByTag("dd");
        System.out.println("dds.size:"+dds.size());
        //得到dd，获取所需数据
        Element dd1 = dds.get(0);
        Elements ems = dd1.getElementsByTag("em");
        Element em = ems.get(0);
        Elements as = em.getElementsByTag("a");
        Element a = as.get(0);
        //val为input数据
//        String val = a.val();
//        System.out.println("val:"+val);
        System.out.println("a:"+a.html());
        
        
        
	}
}















