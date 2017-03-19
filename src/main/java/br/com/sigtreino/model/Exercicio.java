package br.com.sigtreino.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.sigtreino.enums.AtividadeFisicaEnum;
import br.com.sigtreino.enums.GrupoMuscularEnum;

@Entity
public class Exercicio {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private AtividadeFisicaEnum atividadeFisica;
	
	@Enumerated(EnumType.STRING)
	private GrupoMuscularEnum grupamento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academia_id")
	private Academia academia;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public AtividadeFisicaEnum getAtividadeFisica() {
		return atividadeFisica;
	}
	public void setAtividadeFisica(AtividadeFisicaEnum atividadeFisica) {
		this.atividadeFisica = atividadeFisica;
	}
	public GrupoMuscularEnum getGrupamento() {
		return grupamento;
	}
	public void setGrupamento(GrupoMuscularEnum grupamento) {
		this.grupamento = grupamento;
	}
	public Academia getAcademia() {
		return academia;
	}
	public void setAcademia(Academia academia) {
		this.academia = academia;
	}
}
