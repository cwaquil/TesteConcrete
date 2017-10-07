package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exception.BusinessException;
import com.util.ClientErrorInformation;

public abstract class MainController {

	@ExceptionHandler(BusinessException.class)
	public static ResponseEntity<ClientErrorInformation> regrasException(HttpServletRequest req, BusinessException e) {
		return new ResponseEntity<ClientErrorInformation>(new ClientErrorInformation(e), e.getStatus());
	}
}
