package com.client.service.response;

import javax.persistence.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


public class Response {
	
	private String message;
	private HttpStatus httpStatus;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public Response(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
	
	
	
	

}
