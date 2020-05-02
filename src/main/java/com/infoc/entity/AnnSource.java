package com.infoc.entity;

public enum AnnSource {
	SJS("上交所"),JCW("巨潮网");
	private String sourcename;

	private AnnSource(String sourcename) {
		this.sourcename = sourcename;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}
	
}
