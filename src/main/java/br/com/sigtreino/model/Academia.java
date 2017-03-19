package br.com.sigtreino.model;

import javax.persistence.Entity;

@Entity
public class Academia extends Pessoa{
	private String razaoSocialNomeEmpresario;
	private String nomeFantasiaEmpresarial;	
	private String login;
	private String senha;
	
	public String getRazaoSocialNomeEmpresario() {
		return razaoSocialNomeEmpresario;
	}
	public void setRazaoSocialNomeEmpresario(String razaoSocialNomeEmpresario) {
		this.razaoSocialNomeEmpresario = razaoSocialNomeEmpresario;
	}
	public String getNomeFantasiaEmpresarial() {
		return nomeFantasiaEmpresarial;
	}
	public void setNomeFantasiaEmpresarial(String nomeFantasiaEmpresarial) {
		this.nomeFantasiaEmpresarial = nomeFantasiaEmpresarial;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
