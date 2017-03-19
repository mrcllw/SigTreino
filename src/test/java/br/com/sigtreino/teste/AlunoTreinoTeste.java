package br.com.sigtreino.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sigtreino.enums.DiaTreinoEnum;
import br.com.sigtreino.enums.GrupoMuscularEnum;
import br.com.sigtreino.enums.TipoTreinoEnum;
import br.com.sigtreino.model.Aluno;
import br.com.sigtreino.model.AlunoTreino;
import br.com.sigtreino.model.Atividade;
import br.com.sigtreino.model.Exercicio;
import br.com.sigtreino.model.Treino;
import br.com.sigtreino.repository.AlunoRepository;
import br.com.sigtreino.repository.AlunoTreinoRepository;
import br.com.sigtreino.repository.ExercicioRepository;
import br.com.sigtreino.repository.TreinoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoTreinoTeste {
	
	@Autowired
	private AlunoTreinoRepository alunoTreinoRep;
	
	@Autowired
	private ExercicioRepository exercicioRep;
	
	@Autowired
	private AlunoRepository alunoRep;
	
	@Autowired
	private TreinoRepository treinoRep;
	
	private Exercicio exercicio;
	private Atividade atividade;
	private Treino treino;
	private Aluno aluno;
	private AlunoTreino alunoTreino;
	
	@Before
	public void init(){
		exercicio = new Exercicio();
		exercicio.setNome("nomeExercicio");
		exercicio.setDescricao("descricaoExercicio");
		exercicio.setGrupamento(GrupoMuscularEnum.Bíceps);
		
		Exercicio exercicioSalvo = exercicioRep.save(exercicio);
	
		aluno = new Aluno();
		aluno.setNome("nomeAluno");
		
		Aluno alunoSalvo = alunoRep.save(aluno);
		
		atividade = new Atividade();
		atividade.setExercicio(exercicioSalvo);
		atividade.setSerie(3);
		atividade.setRepeticao(10);
		
		treino = new Treino();
		treino.setNome("nomeTreino");
		treino.setTipo(TipoTreinoEnum.A);
		List<Atividade> atividades = new ArrayList<>();
		atividades.add(atividade);
		treino.setAtividades(atividades);
		
		Treino treinoSalvo = treinoRep.save(treino);
		
		alunoTreino = new AlunoTreino();
		alunoTreino.setAluno(alunoSalvo);
		alunoTreino.setTreino(treinoSalvo);
		List<DiaTreinoEnum> dias = new ArrayList<>();
		dias.add(DiaTreinoEnum.Segunda);
		alunoTreino.setDias(dias);
		
		alunoTreinoRep.save(alunoTreino);
	}
	
	@Test
	@Transactional
	public void salvar(){
		assertNotNull(alunoTreino.getId());
	}
	
	@Test
	public void editar(){		
		AlunoTreino alunoTreinoNovo = alunoTreinoRep.findOne(alunoTreino.getId());
	
		List<DiaTreinoEnum> dias = new ArrayList<DiaTreinoEnum>();
		dias.add(DiaTreinoEnum.Sexta);
		dias.add(DiaTreinoEnum.Domingo);
		alunoTreinoNovo.setDias(dias);
		
		AlunoTreino alunoTreinoEditado = alunoTreinoRep.save(alunoTreinoNovo);
		
		assertNotEquals(alunoTreino, alunoTreinoEditado);
	}
	
	@Test
	@Transactional
	public void exluir(){
		alunoTreinoRep.delete(alunoTreino);
		AlunoTreino alunoExcluido = alunoTreinoRep.findOne(alunoTreino.getId());
		assertEquals(alunoExcluido, null);
	}
	
	@Test
	public void buscarTodos(){
		List<AlunoTreino> todosAlunoTreino = alunoTreinoRep.findAll();
		assertTrue(todosAlunoTreino.size() > 0);
	}
}
