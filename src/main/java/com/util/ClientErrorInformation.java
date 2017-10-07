package com.util;

import com.exception.BusinessException;

public class ClientErrorInformation {

	private String mensagem;

	public ClientErrorInformation(String mensagem) {
		this.setMensagem(mensagem);
	}

	public ClientErrorInformation(BusinessException e) {
		this.setMensagem(e.getMessage());
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
