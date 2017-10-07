package com.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dto.LoginTO;
import com.entity.Phone;
import com.entity.Usuario;
import com.exception.BusinessException;
import com.exception.EmailDuplicadoException;
import com.exception.EmailInvalidoException;
import com.exception.SenhaInvalidaException;

@Transactional
@Repository
public class UsuarioDAO extends GenericDAO<Usuario> {

	private Map<String, Object> parameters;

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public void consultaEmailDuplicado(String email) throws BusinessException {
		parameters = new HashMap<String, Object>();

		parameters.put("email", email);

		Usuario usuarioExistente = findOneResult("Usuario.consultaPorEmail", parameters);

		if (null != usuarioExistente) {
			throw new EmailDuplicadoException("E-mail já existente");
		}

	}

	public Usuario consultaUsuarioLogin(LoginTO loginTO) throws BusinessException {
		parameters = new HashMap<String, Object>();

		parameters.put("email", loginTO.getUsername());

		Usuario usuarioExistente = findOneResult("Usuario.consultaPorEmail", parameters);

		if (null == usuarioExistente) {
			throw new EmailInvalidoException("Usuário e/ou senha inválidos");
		}

		if (!loginTO.getPassword().equals(usuarioExistente.getPassword())) {
			throw new SenhaInvalidaException("Usuário e/ou senha inválidos");
		}

		return usuarioExistente;
	}

	public Usuario consultaUsuarioPorId(UUID id) throws BusinessException {
		parameters = new HashMap<String, Object>();

		parameters.put("idUsuario", id);

		Usuario usuarioExistente = findOneResult("Usuario.consultaPorId", parameters);

		if (null == usuarioExistente) {
			throw new EmailInvalidoException("Não Autorizado");
		}

		return usuarioExistente;
	}

	public void saveUsuario(Usuario usuario) {

		for (Phone phone : usuario.getPhones()) {
			phone.setUsuario(usuario);
		}
		save(usuario);

	}
}
