package com.parkapi.park.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserPasswordDTO {

	@NotBlank
	@Size(min = 6, max = 6)
	private String senhaAtual;
	@NotBlank
	@Size(min = 6, max = 6)
	private String novaSenha;
	@NotBlank
	@Size(min = 6, max = 6)
	private String confirmaSenha;
	
	public UserPasswordDTO() {
	}

	public UserPasswordDTO(String senhaAtual, String novaSenha, String confirmaSenha) {
		super();
		this.senhaAtual = senhaAtual;
		this.novaSenha = novaSenha;
		this.confirmaSenha = confirmaSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	
}
