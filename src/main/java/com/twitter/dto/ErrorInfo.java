package com.twitter.dto;

/**
 * Error details which will be sent as json to the user will be set in this DTO.
 *
 * @author Veenit Kumar
 * @since 10-12-2016
 */
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
	 * @param statusCode the fieldName to set
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
