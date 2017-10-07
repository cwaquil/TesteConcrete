package com.exception;

import org.springframework.http.HttpStatus;

public class EmailDuplicadoException extends BusinessException {

	private static final long serialVersionUID = 3787714445750793679L;

	public EmailDuplicadoException(String string) {
		super(string, HttpStatus.NOT_ACCEPTABLE);
	}

}
