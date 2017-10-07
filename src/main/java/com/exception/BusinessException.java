package com.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -6915229778821330567L;

	private HttpStatus status;

	public BusinessException(String string, HttpStatus httpStatus) {
		super(string);
		this.setStatus(httpStatus);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
