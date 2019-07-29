package com.cafe24.mall;

public enum MemberEnum {
	BRONZE("1"), SILVER("2"), GOLD("3"), PLATINUM("4"), DIAMOND("5");
	private String name;
 
	MemberEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
