package com.prj.api.fileApp.Exception;

public class ExceptionJSON {

	private int code;
	private String url;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ExceptionJSON [code=" + code + ", url=" + url
				+ ", message=" + message + "]";
	}
}