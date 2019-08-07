package com.example.project_frontend.frontend.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class CategoryVo {
	private long no;
	@NotEmpty
	private String top_category;
	@NotEmpty
	private String low_category;

	public long getNo() {
		return no;
	}

	public String getTop_category() {
		return top_category;
	}

	public String getLow_category() {
		return low_category;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public void setTop_category(String top_category) {
		this.top_category = top_category;
	}

	public void setLow_category(String low_category) {
		this.low_category = low_category;
	}

	@Override
	public String toString() {
		return "CagtegoryVo [no=" + no + ", top_category=" + top_category + ", low_category=" + low_category + "]";
	}

}
