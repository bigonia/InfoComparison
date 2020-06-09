package com.infoc.test;

import java.io.IOException;
import java.nio.charset.Charset;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.infoc.data.DataSource;

/**
 * 	测试从上交所抓取的数据是否符合预期、打印一部分上交所的数据
 * 	update from: 测试数据抓取和处理的内部逻辑
 * @author Administrator
 *
 */
public class TestSJSh {
	
	public static String sjsurl = "http://www.sse.com.cn/disclosure/listedinfo/bulletin/s_docdatesort_desc_2019openpdf.htm";
	
	/**
	 * 	如果输入data数据，则用data数据生成Document用相同的逻辑进行处理，
	 *	 若data为null；使用从网站抓取的数据，以此对比。
	 * @param doc
	 * @throws IOException
	 */
	public void SJSTest(String data) throws IOException {
		
		//document
		Document document = null;
		//获取源数据
		String htmlText = new DataSource().getStringData(sjsurl);
		document = Jsoup.parse(htmlText);
		//判断是否传入检测数据
		if(data!=null) {
			document = Jsoup.parse(data);
			System.out.println("生成Document;测试传入的String");
		};
        //获取tag div
        Elements divs = document.getElementsByTag("div");
        System.out.println("获取div标签;div.size:"+divs.size());
        Element div = divs.get(0);
        //打印div
        //System.out.println("printdiv:"+div.html());
        //得到div,获取dl
        Elements dls = div.getElementsByTag("dl");
        System.out.println("获取dl标签;dls.size:"+dls.size());
        Element dl = dls.get(0);
        //得到dl，获取dd
        Elements dds = dl.getElementsByTag("dd");
        System.out.println("获取dd标签;dds.size:"+dds.size());
        //得到dd，获取所需数据
        Element dd1 = dds.get(0);
        Elements ems = dd1.getElementsByTag("em");
        Element em = ems.get(0);
        Elements as = em.getElementsByTag("a");
        Element a = as.get(0);
        //val为input数据
//		        String val = a.val();
//		        System.out.println("val:"+val);
        System.out.println("打印a标签:"+a.html());
        
        
	}
	
	public static void main(String[] args) {
		try {
			new TestSJSh().SJSTest(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}















