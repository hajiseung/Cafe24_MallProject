package com.cafe24.mall.vo;

public class OrderVo {
	// purchase_page
	private long no;// 주문 페이지 no
	private long basket_no;// 장바구니 no
	private long total_price; // 장바구니 물품 가격 총 합
	private String shipping;// 수령배송지
	private String b_member;// 수령인
	private String b_tell_ph;// 일반전화
	private String b_cell_ph;// 휴대전화
	private String memo;// 배송메모
	private String order_identify;// 주문식별번호
	private String account;// 계좌번호

	// payment
	private String method_of_payment;

	public long getNo() {
		return no;
	}

	public long getBasket_no() {
		return basket_no;
	}

	public long getTotal_price() {
		return total_price;
	}

	public String getShipping() {
		return shipping;
	}

	public String getB_member() {
		return b_member;
	}

	public String getB_tell_ph() {
		return b_tell_ph;
	}

	public String getB_cell_ph() {
		return b_cell_ph;
	}

	public String getMemo() {
		return memo;
	}

	public String getOrder_identify() {
		return order_identify;
	}

	public String getAccount() {
		return account;
	}

	public String getMethod_of_payment() {
		return method_of_payment;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public void setBasket_no(long basket_no) {
		this.basket_no = basket_no;
	}

	public void setTotal_price(long total_price) {
		this.total_price = total_price;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public void setB_member(String b_member) {
		this.b_member = b_member;
	}

	public void setB_tell_ph(String b_tell_ph) {
		this.b_tell_ph = b_tell_ph;
	}

	public void setB_cell_ph(String b_cell_ph) {
		this.b_cell_ph = b_cell_ph;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setOrder_identify(String order_identify) {
		this.order_identify = order_identify;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setMethod_of_payment(String method_of_payment) {
		this.method_of_payment = method_of_payment;
	}

	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", basket_no=" + basket_no + ", total_price=" + total_price + ", shipping="
				+ shipping + ", b_member=" + b_member + ", b_tell_ph=" + b_tell_ph + ", b_cell_ph=" + b_cell_ph
				+ ", memo=" + memo + ", order_identify=" + order_identify + ", account=" + account
				+ ", method_of_payment=" + method_of_payment + "]";
	}

}
