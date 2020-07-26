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
	
	public Announcement() {
		super();
	}
	@Override
	public String toString() {
		return "Announcement [code=" + code + ", title=" + title + ", time=" + time + ", source=" + source + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Announcement other = (Announcement) obj;
		if (code != other.code)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
}
