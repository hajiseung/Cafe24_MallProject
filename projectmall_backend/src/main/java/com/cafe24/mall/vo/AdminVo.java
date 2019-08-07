package com.cafe24.mall.vo;

public class AdminVo {

	private String id;
	private String pw;

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "AdminVo [id=" + id + ", pw=" + pw + "]";
	}
}
