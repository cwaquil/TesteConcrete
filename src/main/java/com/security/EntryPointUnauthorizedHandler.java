package com.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.controller.ExceptionControllerAdvice;

@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

	@Autowired
	private ExceptionControllerAdvice exceptionControllerAdvice;

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse res, AuthenticationException e)
			throws IOException, ServletException {
		exceptionControllerAdvice.exceptionHandlerPrinter(e, res);
	}

}