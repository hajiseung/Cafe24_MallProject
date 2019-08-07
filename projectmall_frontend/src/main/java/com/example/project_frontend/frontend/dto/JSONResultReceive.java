package com.example.project_frontend.frontend.dto;

public class JSONResultReceive {
	private String result; // success , fail
	private String message; // if fail , set
	private Object data; // if success, set

	public static JSONResultReceive success(Object data) { // data = exist
		return new JSONResultReceive("success", null, data);
	}

	public static JSONResultReceive fail(String message) {
		return new JSONResultReceive("fail", message, null);
	}

	private JSONResultReceive(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	@Override
	public String toString() {
		return "JSONResult [result=" + result + ", message=" + message + ", data=" + data + "]";
	}
}