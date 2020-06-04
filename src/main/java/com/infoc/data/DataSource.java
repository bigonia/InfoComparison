package com.infoc.data;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.sf.json.JSONObject;
/**
 * 	抓取源数据
 * @author Administrator
 *
 */
public class DataSource {
//	private final 
//	private  String jcwurl = "http://www.cninfo.com.cn/new/index/getAnnouces?type=sh";
//	private  String sjsurl = "http://www.sse.com.cn/disclosure/listedinfo/bulletin/s_docdatesort_desc_2019openpdf.htm";
	
	/**
	 * 	发送post请求，获得json数据
	 * 		获得巨潮网的数据：post请求需要不断地更改参数来查询所有数据
	 * @param url
	 * @param content
	 * @return JSONObject
	 * @throws IOException
	 */
	public JSONObject getJsonDataByPost(String url,String content) throws IOException {
		// Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(url);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();     
        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        connection.setRequestMethod("POST");      
        // Post 请求不能使用缓存
        connection.setUseCaches(false);  
           //设置本次连接是否自动重定向 
        connection.setInstanceFollowRedirects(true);      
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection
                .getOutputStream());
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
//        String content = "字段名=" + URLEncoder.encode("字符串值", "编码");
//        String content = "pageNum=2&pageSize=30&column=szse&tabName=fulltext&plate=sh&stock=&searchkey=&secid=&category=&trade=&seDate=2020-05-14~2020-05-14&sortName=&sortType=&isHLtitle=true";
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        out.writeBytes(content);
        //流用完记得关
        out.flush();
        out.close();
        //获取响应
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        StringBuilder sb = new StringBuilder();  
        //写入
        int cp;  
        while ((cp = br.read()) != -1) {  
          sb.append((char) cp);  
        }  
        br.close();
        //该干的都干完了,记得把连接断了
        connection.disconnect();
        
        //获得json数据
        String jsonText = sb.toString();  
        return JSONObject.fromObject(jsonText);
        
	}
	
	
	/**
	 * 	发送get请求，获得json数据
	 * 		巨潮网:get请求返回的数据不是全部。
	 * @param url
	 * @return JSONObject
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public JSONObject getJsonDataByGet(String url) throws MalformedURLException, IOException {
		//读取网站数据
		InputStream is = new URL(url).openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));  
        StringBuilder sb = new StringBuilder();  
        //写入
        int cp;  
        while ((cp = br.read()) != -1) {  
          sb.append((char) cp);  
        }  
        is.close();
        br.close();
        //得到json
        String jsonText = sb.toString();  
		
        return JSONObject.fromObject(jsonText);
        
//		return JSONObject.fromObject(Jsoup.connect(url).get());
		
	}
	
	/**
	 * 	使用Jsoup直接获得网页源码，返回为Document
	 * 		上交所以HTML的形式返回了全部数据，之间用Jsoup解析即可
	 * @param url
	 * @return	Document
	 * @throws IOException
	 */
	public Document getHtmlData(String url) throws IOException {
		
		return Jsoup.connect(url).get();
		
	}
	
	/**
	 * 	统一从两个网站抓取数据的方法,抓取结果转换为String返回;
	 * 		需要修改为：传入content即为post，无则是get
	 * 	tip：1使用String实例化document
	 * 		 2使用String实例化JSONObject
	 * @param url
	 * @return	String
	 * @throws IOException
	 */
	public String getStringData(String url) throws IOException {
		
//		String JCWurl = "http://www.cninfo.com.cn/new/index/getAnnouces?type=sh";
//		String SJSurl = "http://www.sse.com.cn/disclosure/listedinfo/bulletin/s_docdatesort_desc_2019openpdf.htm";
		//字符输入流
		InputStream is = null;
		//转换流
		InputStreamReader isr = null;
		//缓冲字符输入流
		BufferedReader br = null;
		
		is = new URL(url).openStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		
		StringBuffer sb = new StringBuffer();
		int cp;
		while((cp = br.read()) != -1) {
			sb.append((char)cp);
		}
		//测试SJS
//		String str = sb.toString();
//		System.out.println(str);
//		new TestSJSh().SJSTest(str);
		
		return sb.toString();
		
	}
	
	
}
