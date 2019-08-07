package com.example.project_frontend.frontend.vo;

public class BasketVo {

	private long no;
	private long item_no;
	private long member_no;
	private long nonmember_no;
	private long option_no;
	private long item_count; // 물품 수량
	private long accmulate; // 적립 예정 금액
	private long total_item_price; // 물품별 총 가격

	private NonUserVo nonUserVo;

	public NonUserVo getNonUserVo() {
		return nonUserVo;
	}

	public void setNonUserVo(NonUserVo nonUserVo) {
		this.nonUserVo = nonUserVo;
	}

	public long getNo() {
		return no;
	}

	public long getItem_no() {
		return item_no;
	}

	public long getMember_no() {
		return member_no;
	}

	public long getNonmember_no() {
		return nonmember_no;
	}

	public long getOption_no() {
		return option_no;
	}

	public long getItem_count() {
		return item_count;
	}

	public long getAccmulate() {
		return accmulate;
	}

	public long getTotal_item_price() {
		return total_item_price;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public void setItem_no(long item_no) {
		this.item_no = item_no;
	}

	public void setMember_no(long member_no) {
		this.member_no = member_no;
	}

	public void setNonmember_no(long nonmember_no) {
		this.nonmember_no = nonmember_no;
	}

	public void setOption_no(long option_no) {
		this.option_no = option_no;
	}

	public void setItem_count(long item_count) {
		this.item_count = item_count;
	}

	public void setAccmulate(long accmulate) {
		this.accmulate = accmulate;
	}

	public void setTotal_item_price(long total_item_price) {
		this.total_item_price = total_item_price;
	}

	@Override
	public String toString() {
		return "BasketVo [no=" + no + ", item_no=" + item_no + ", member_no=" + member_no + ", nonmember_no="
				+ nonmember_no + ", option_no=" + option_no + ", item_count=" + item_count + ", accmulate=" + accmulate
				+ ", total_item_price=" + total_item_price + ", nonUserVo=" + nonUserVo + "]";
	}

}
