package com.example.project_frontend.frontend.dto;

public class JSONResult<T> {
	private String result; // success, fail
	private String message; // if fail, set
	private T data; // if success, set

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JSONResult [result=" + result + ", message=" + message + ", data=" + data + "]";
	}

}
