package com.exception;

import org.springframework.http.HttpStatus;

public class SenhaInvalidaException extends BusinessException {

	private static final long serialVersionUID = 3787714445750793679L;

	public SenhaInvalidaException(String string) {
		super(string, HttpStatus.UNAUTHORIZED);
	}

}
