package com.util;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.entity.Usuario;
import com.exception.BusinessException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {

	public static final String SECRET = "SpecialKey";
	public static final String TOKEN_PREFIX = "Token";

	public static String tokenGen(Usuario usuario) {

		Claims claims = Jwts.claims().setSubject(usuario.getEmail());
		claims.put("userId", usuario.getIdUsuario().toString());

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	public static Usuario parseBack(String token) throws BusinessException {
		try {
			Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
			Usuario usuario = new Usuario();
			usuario.setEmail(body.getSubject());
			usuario.setIdUsuario(UUID.fromString((String) body.get("userId")));
			return usuario;
		} catch (Exception e) {
			throw new BusinessException("Não Autorizado", HttpStatus.UNAUTHORIZED);
		}

	}

}
