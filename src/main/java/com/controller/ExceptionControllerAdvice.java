package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.ClientErrorInformation;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public void exceptionHandlerPrinter(Exception e, HttpServletResponse res) throws IOException {
		ResponseEntity<ClientErrorInformation> erro = new ResponseEntity<ClientErrorInformation>(
				new ClientErrorInformation("Não Autorizado"), HttpStatus.UNAUTHORIZED);
		res.setStatus(erro.getStatusCodeValue());
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = res.getWriter();
		out.print(mapper.writeValueAsString(erro.getBody()));
		out.flush();
	}

}
