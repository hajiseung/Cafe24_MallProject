package com.cafe24.mall.vo;

public class NonUserVo {

	private long no;
	private String mac_addr;

	public long getNo() {
		return no;
	}

	public String getMac_addr() {
		return mac_addr;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public void setMac_addr(String mac_addr) {
		this.mac_addr = mac_addr;
	}

	@Override
	public String toString() {
		return "NonUserVo [no=" + no + ", mac_addr=" + mac_addr + "]";
	}

}
