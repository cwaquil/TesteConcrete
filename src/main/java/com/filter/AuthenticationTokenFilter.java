package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.controller.ExceptionControllerAdvice;
import com.service.LoginService;

@Component
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private LoginService loginService;

	@Autowired
	private ExceptionControllerAdvice exceptionControllerAdvice;

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		try {
			if (!"/login".equals(req.getServletPath())) {
				String token = req.getHeader("Authorization");

				if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = loginService.loadUserAutenticacao(token);

					if (null != userDetails) {
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				}
			}

		} catch (Exception e) {
			exceptionControllerAdvice.exceptionHandlerPrinter(e, res);
			return;
		}
		chain.doFilter(request, response);

	}
}
