package com.twitter.dto;

public class ErrorInfo {
	private int statusCode;
	private String message;
	
	/**
	 * @return the fieldName
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}	
	
	
}
