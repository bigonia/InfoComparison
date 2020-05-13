package com.infoc.entity;

public class Announcement {
	private int code;
	private String title;
	private String time;
	private AnnSource source;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public AnnSource getSource() {
		return source;
	}
	public void setSource(AnnSource source) {
		this.source = source;
	}
	public Announcement(int code, String title, String time) {
		super();
		this.code = code;
		this.title = title;
		this.time = time;
	}
	public Announcement(int code, String title, String time, AnnSource source) {
		super();
		this.code = code;
		this.title = title;
		this.time = time;
		this.source = source;
	}
	
	
	
}
