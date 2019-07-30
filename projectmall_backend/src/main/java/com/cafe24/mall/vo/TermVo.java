package com.cafe24.mall.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class TermVo {
	private long no;
	@NotEmpty
	private String title;
	@NotEmpty
	private String contents;
	private String reg_date;
	@NotEmpty
	private String registrant;
	private String update_date;
	private String modifier;
	@NotNull
	private boolean isnecessary;

	public long getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public String getReg_date() {
		return reg_date;
	}

	public String getRegistrant() {
		return registrant;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public String getModifier() {
		return modifier;
	}

	public boolean isIsnecessary() {
		return isnecessary;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setIsnecessary(boolean isnecessary) {
		this.isnecessary = isnecessary;
	}

	@Override
	public String toString() {
		return "TermVo [no=" + no + ", title=" + title + ", contents=" + contents + ", reg_date=" + reg_date
				+ ", registrant=" + registrant + ", update_date=" + update_date + ", modifier=" + modifier
				+ ", isnecessary=" + isnecessary + "]";
	}

}
