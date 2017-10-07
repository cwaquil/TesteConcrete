package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LoginTO;
import com.entity.Usuario;
import com.exception.BusinessException;
import com.service.UsuarioService;

@RestController
public class LoginController extends MainController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=*/*")
	public @ResponseBody Usuario logar(@RequestBody LoginTO loginTO) throws BusinessException {

		return usuarioService.logar(loginTO);
	}
}
