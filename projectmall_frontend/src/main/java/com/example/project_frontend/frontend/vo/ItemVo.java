package com.example.project_frontend.frontend.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ItemVo {
	// item
	private long no;
	private long category_no;
	private String top_category;
	private String low_category;
	@NotNull
	private String title;
	private String desc_html;

	// item_detail
	private long item_detail_no;
	@NotNull
	private long amount;
	@NotNull
	private long available_amount;

	@NotNull
	private Long price;
	@NotNull
	private boolean non_amount;
	@NotNull
	private boolean displaystatus;
	@NotNull
	private boolean salestatus;
	private String reg_date;

	// item_photo
	private List<MultipartFile> multiPartPhoto;
	private List<String> photo;
	private List<Boolean> is_main;
	private String main_photo;

	// item_option
	private List<String> name;

	// item_category
	private CategoryVo categoryVo;

	private BasketVo basketVo;

	public long getNo() {
		return no;
	}

	public long getCategory_no() {
		return category_no;
	}

	public String getTop_category() {
		return top_category;
	}

	public String getLow_category() {
		return low_category;
	}

	public String getTitle() {
		return title;
	}

	public String getDesc_html() {
		return desc_html;
	}

	public long getItem_detail_no() {
		return item_detail_no;
	}

	public long getAmount() {
		return amount;
	}

	public long getAvailable_amount() {
		return available_amount;
	}

	public Long getPrice() {
		return price;
	}

	public boolean isNon_amount() {
		return non_amount;
	}

	public boolean isDisplaystatus() {
		return displaystatus;
	}

	public boolean isSalestatus() {
		return salestatus;
	}

	public String getReg_date() {
		return reg_date;
	}

	public List<MultipartFile> getMultiPartPhoto() {
		return multiPartPhoto;
	}

	public List<String> getPhoto() {
		return photo;
	}

	public List<Boolean> getIs_main() {
		return is_main;
	}

	public String getMain_photo() {
		return main_photo;
	}

	public List<String> getName() {
		return name;
	}

	public CategoryVo getCategoryVo() {
		return categoryVo;
	}

	public BasketVo getBasketVo() {
		return basketVo;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public void setCategory_no(long category_no) {
		this.category_no = category_no;
	}

	public void setTop_category(String top_category) {
		this.top_category = top_category;
	}

	public void setLow_category(String low_category) {
		this.low_category = low_category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDesc_html(String desc_html) {
		this.desc_html = desc_html;
	}

	public void setItem_detail_no(long item_detail_no) {
		this.item_detail_no = item_detail_no;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public void setAvailable_amount(long available_amount) {
		this.available_amount = available_amount;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public void setNon_amount(boolean non_amount) {
		this.non_amount = non_amount;
	}

	public void setDisplaystatus(boolean displaystatus) {
		this.displaystatus = displaystatus;
	}

	public void setSalestatus(boolean salestatus) {
		this.salestatus = salestatus;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public void setMultiPartPhoto(List<MultipartFile> multiPartPhoto) {
		this.multiPartPhoto = multiPartPhoto;
	}

	public void setPhoto(List<String> photo) {
		this.photo = photo;
	}

	public void setIs_main(List<Boolean> is_main) {
		this.is_main = is_main;
	}

	public void setMain_photo(String main_photo) {
		this.main_photo = main_photo;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public void setCategoryVo(CategoryVo categoryVo) {
		this.categoryVo = categoryVo;
	}

	public void setBasketVo(BasketVo basketVo) {
		this.basketVo = basketVo;
	}

	@Override
	public String toString() {
		return "ItemVo [no=" + no + ", category_no=" + category_no + ", top_category=" + top_category
				+ ", low_category=" + low_category + ", title=" + title + ", desc_html=" + desc_html
				+ ", item_detail_no=" + item_detail_no + ", amount=" + amount + ", available_amount=" + available_amount
				+ ", price=" + price + ", non_amount=" + non_amount + ", displaystatus=" + displaystatus
				+ ", salestatus=" + salestatus + ", reg_date=" + reg_date + ", multiPartPhoto=" + multiPartPhoto
				+ ", photo=" + photo + ", is_main=" + is_main + ", main_photo=" + main_photo + ", name=" + name
				+ ", categoryVo=" + categoryVo + ", basketVo=" + basketVo + "]";
	}

}
