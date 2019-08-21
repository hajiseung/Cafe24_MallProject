package com.example.project_frontend.frontend.vo;

public class PaymentVo {
	
	private long no;
	private String method_of_payment;

	public long getNo() {
		return no;
	}

	public String getMethod_of_payment() {
		return method_of_payment;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public void setMethod_of_payment(String method_of_payment) {
		this.method_of_payment = method_of_payment;
	}

	@Override
	public String toString() {
		return "PaymentVo [no=" + no + ", method_of_payment=" + method_of_payment + "]";
	}

}
