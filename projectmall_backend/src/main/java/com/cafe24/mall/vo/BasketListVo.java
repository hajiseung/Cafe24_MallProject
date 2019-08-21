package com.cafe24.mall.vo;

public class BasketListVo {
	private int item_no;
	private int item_count;
	private int accmulate;
	private int total_item_price;
	private String title;
	private int price;
	private String main_photo;

	public int getItem_no() {
		return item_no;
	}

	public int getItem_count() {
		return item_count;
	}

	public int getAccmulate() {
		return accmulate;
	}

	public int getTotal_item_price() {
		return total_item_price;
	}

	public String getTitle() {
		return title;
	}

	public int getPrice() {
		return price;
	}

	public String getMain_photo() {
		return main_photo;
	}

	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}

	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}

	public void setAccmulate(int accmulate) {
		this.accmulate = accmulate;
	}

	public void setTotal_item_price(int total_item_price) {
		this.total_item_price = total_item_price;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setMain_photo(String main_photo) {
		this.main_photo = main_photo;
	}

	@Override
	public String toString() {
		return "BasketListVo [item_no=" + item_no + ", item_count=" + item_count + ", accmulate=" + accmulate
				+ ", total_item_price=" + total_item_price + ", title=" + title + ", price=" + price + ", main_photo="
				+ main_photo + "]";
	}

}
