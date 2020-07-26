package com.infoc.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.infoc.entity.Announcement;

/**
 * 	文件工具栏，实例化抓取到的数据
 * @author Administrator
 *
 */
public class FileUtils {
	
	public void saveInfo(List<Announcement> anns) throws IOException {
		String path = "C:\\Users\\Administrator\\Desktop\\temp.txt";
		File data = new File(path);
		FileWriter writer = new FileWriter(data);
		for (Announcement ann : anns) {
			writer.write(ann.toString()+"\r\n");
		}
		writer.close();
		System.out.println("save over!");
	}
	
	
	
}
