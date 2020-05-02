package com.infoc.entity;

public enum AnnStatus {
	/**
	 * 	缺失、延迟、...
	 */
	MISS,DALAY,ERROR;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

//	private AnnStatus(String msg) {
//		this.msg = msg;
//	}
	
	
	
}
