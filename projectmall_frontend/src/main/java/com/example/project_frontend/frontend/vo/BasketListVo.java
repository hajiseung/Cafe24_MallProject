package com.example.project_frontend.frontend.vo;

import java.util.List;

public class BasketListVo {

	private int item_count;
	private int accmulate;
	private int total_item_price;
	private int item_no;
	private String title;
	private int price;
	private String main_photo;

	private List<BasketListVo> basketListVo;

	public int getItem_count() {
		return item_count;
	}

	public int getAccmulate() {
		return accmulate;
	}

	public int getTotal_item_price() {
		return total_item_price;
	}

	public int getItem_no() {
		return item_no;
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

	public List<BasketListVo> getBasketListVo() {
		return basketListVo;
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

	public void setItem_no(int item_no) {
		this.item_no = item_no;
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

	public void setBasketListVo(List<BasketListVo> basketListVo) {
		this.basketListVo = basketListVo;
	}

	@Override
	public String toString() {
		return "BasketListVo [item_count=" + item_count + ", accmulate=" + accmulate + ", total_item_price="
				+ total_item_price + ", item_no=" + item_no + ", title=" + title + ", price=" + price + ", main_photo="
				+ main_photo + ", basketListVo=" + basketListVo + "]";
	}

}
