package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UsuarioDAO;
import com.dto.LoginTO;
import com.dto.UsuarioTO;
import com.entity.Usuario;
import com.exception.BusinessException;
import com.util.JWTUtils;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	private Usuario preparaUsuario(UsuarioTO usuarioTO) {

		Usuario usuario = new Usuario();

		usuario.setIdUsuario(UUID.randomUUID());
		usuario.setName(usuarioTO.getName());
		usuario.setEmail(usuarioTO.getEmail());
		usuario.setPassword(usuarioTO.getPassword());
		usuario.setPhones(usuarioTO.getPhones());
		usuario.setCreated(new Date());
		usuario.setModified(new Date());
		usuario.setLastLogin(null);
		usuario.setToken(JWTUtils.tokenGen(usuario));

		return usuario;
	}

	public void insereAdmin() {
		Usuario usuario = new Usuario();

		usuario.setIdUsuario(UUID.fromString("ee30869a-702c-4729-b1fb-c36bdffa8913"));
		usuario.setName("admin");
		usuario.setEmail("admin@silva.org");
		usuario.setPassword("password");
		usuario.setPhones(new ArrayList<>());
		usuario.setCreated(new Date());
		usuario.setModified(new Date());
		usuario.setLastLogin(null);
		usuario.setToken(JWTUtils.tokenGen(usuario));

		usuarioDAO.saveUsuario(usuario);
	}

	public Usuario cadastrar(UsuarioTO usuarioTO) throws BusinessException {

		usuarioDAO.consultaEmailDuplicado(usuarioTO.getEmail());

		Usuario usuario = preparaUsuario(usuarioTO);

		usuarioDAO.saveUsuario(usuario);

		return usuario;
	}

	public Usuario logar(LoginTO loginTO) throws BusinessException {

		Usuario usuario = usuarioDAO.consultaUsuarioLogin(loginTO);

		usuario.setLastLogin(new Date());

		usuarioDAO.update(usuario);

		return usuario;

	}

	public Usuario pesquisaPorId(UUID id) throws BusinessException {

		Usuario usuario = usuarioDAO.consultaUsuarioPorId(id);

		return usuario;

	}

}
