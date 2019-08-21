package com.example.project_frontend.frontend.vo;

import java.util.List;

public class OrderVo {
	// purchase_page
	private long no;// 주문 페이지 no
	private long member_no;// 회원 no
	private List<Long> item_no;// 물품 no
	private long payment_no;// 결제 수단 번호
	private String method_of_payment;// 결제 수단
	private List<Long> total_price; // 장바구니 물품 가격 총 합
	private String shipping;// 수령배송지
	private String b_member;// 수령인
	private String b_cell_ph;// 휴대전화
	private String memo;// 배송메모
	private String account;// 계좌번호

	public long getNo() {
		return no;
	}

	public long getMember_no() {
		return member_no;
	}

	public List<Long> getItem_no() {
		return item_no;
	}

	public long getPayment_no() {
		return payment_no;
	}

	public String getMethod_of_payment() {
		return method_of_payment;
	}

	public List<Long> getTotal_price() {
		return total_price;
	}

	public String getShipping() {
		return shipping;
	}

	public String getB_member() {
		return b_member;
	}

	public String getB_cell_ph() {
		return b_cell_ph;
	}

	public String getMemo() {
		return memo;
	}

	public String getAccount() {
		return account;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public void setMember_no(long member_no) {
		this.member_no = member_no;
	}

	public void setItem_no(List<Long> item_no) {
		this.item_no = item_no;
	}

	public void setPayment_no(long payment_no) {
		this.payment_no = payment_no;
	}

	public void setMethod_of_payment(String method_of_payment) {
		this.method_of_payment = method_of_payment;
	}

	public void setTotal_price(List<Long> total_price) {
		this.total_price = total_price;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public void setB_member(String b_member) {
		this.b_member = b_member;
	}

	public void setB_cell_ph(String b_cell_ph) {
		this.b_cell_ph = b_cell_ph;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", member_no=" + member_no + ", item_no=" + item_no + ", payment_no=" + payment_no
				+ ", method_of_payment=" + method_of_payment + ", total_price=" + total_price + ", shipping=" + shipping
				+ ", b_member=" + b_member + ", b_cell_ph=" + b_cell_ph + ", memo=" + memo + ", account=" + account
				+ "]";
	}

}
