package com.tuan.dictionary.exception;

public class SuccessResponse {
	private int status;
	private String message;
	
	public SuccessResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public SuccessResponse(String message,int status) {
		this.message=message;
		this.status=status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
