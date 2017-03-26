package br.com.sigtreino.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sigtreino.enums.TipoTreinoEnum;

@Entity
public class Treino {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="treino_id")
	private List<Atividade> atividades;
	
	@Enumerated(EnumType.STRING)
	private TipoTreinoEnum tipo;
	
	@JsonIgnore
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
	public List<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	public TipoTreinoEnum getTipo() {
		return tipo;
	}
	public void setTipo(TipoTreinoEnum tipo) {
		this.tipo = tipo;
	}
	public Academia getAcademia() {
		return academia;
	}
	public void setAcademia(Academia academia) {
		this.academia = academia;
	}
}
