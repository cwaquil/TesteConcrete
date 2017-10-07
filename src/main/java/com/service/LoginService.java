package com.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.entity.Usuario;
import com.exception.BusinessException;
import com.util.JWTUtils;

@Component
public class LoginService {

	@Autowired
	private UsuarioService usuarioService;

	public UserDetails loadUserAutenticacao(String tokenHeader) throws BusinessException {

		Usuario usuario = JWTUtils.parseBack(tokenHeader);
		usuario = usuarioService.pesquisaPorId(usuario.getIdUsuario());
		verificaToken(tokenHeader, usuario.getToken());
		verificaRegraSessao(usuario.getLastLogin());
		Collection<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		User usuarioLogado = new User(usuario.getEmail(), usuario.getPassword(), auths);

		return usuarioLogado;
	}

	private void verificaRegraSessao(Date lastLogin) throws BusinessException {

		long parametro = 60000 * 30;
		long diff = new Date().getTime() - lastLogin.getTime();
		if (diff >= parametro) {
			throw new BusinessException("Sessão inválida", HttpStatus.UNAUTHORIZED);
		}

	}

	private void verificaToken(String tokenHeader, String tokenBanco) throws BusinessException {
		if (!tokenHeader.equals(tokenBanco)) {
			throw new BusinessException("Não autorizado", HttpStatus.UNAUTHORIZED);
		}
	}

}
