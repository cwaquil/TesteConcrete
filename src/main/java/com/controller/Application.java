package com.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.exception.BusinessException;
import com.service.UsuarioService;

@SpringBootApplication
@ComponentScan({ "com" })
@EntityScan("com.entity")
@ImportResource(value = "classpath:/hsql_cfg.xml")
public class Application {

	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	private void createAdmin() throws BusinessException {
		usuarioService.insereAdmin();
	}

}