package br.com.sigtreino.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Aluno extends Pessoa{
	private String nome;

	@OneToMany(mappedBy="aluno", cascade=CascadeType.ALL)
	private List<AlunoTreino> treinos;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academia_id")
	private Academia academia;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<AlunoTreino> getTreinos() {
		return treinos;
	}
	public void setTreinos(List<AlunoTreino> treinos) {
		this.treinos = treinos;
	}
	public Academia getAcademia() {
		return academia;
	}
	public void setAcademia(Academia academia) {
		this.academia = academia;
	}
}
