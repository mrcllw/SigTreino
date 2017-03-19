package br.com.sigtreino.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.sigtreino.enums.DiaTreinoEnum;

@Entity
public class AlunoTreino {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="aluno_id")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name="treino_id")
	private Treino treino;
	
	@Column(name="dia")
	@ElementCollection(targetClass=DiaTreinoEnum.class)
	@Enumerated(EnumType.STRING)
	private List<DiaTreinoEnum> dias;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Treino getTreino() {
		return treino;
	}
	public void setTreino(Treino treino) {
		this.treino = treino;
	}
	public List<DiaTreinoEnum> getDias() {
		return dias;
	}
	public void setDias(List<DiaTreinoEnum> dias) {
		this.dias = dias;
	}
}
