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

import br.com.sigtreino.enums.GrupoMuscularEnum;
import br.com.sigtreino.enums.TipoTreinoEnum;
import br.com.sigtreino.model.Academia;
import br.com.sigtreino.model.Atividade;
import br.com.sigtreino.model.Exercicio;
import br.com.sigtreino.model.Treino;
import br.com.sigtreino.repository.AcademiaRepository;
import br.com.sigtreino.repository.ExercicioRepository;
import br.com.sigtreino.repository.TreinoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TreinoTeste {

	@Autowired
	private TreinoRepository treinoRep;
	
	@Autowired
	private ExercicioRepository exercicioRep;
	
	private Exercicio exercicio;
	private Atividade atividade;
	private Treino treino;
	
	@Before
	public void init(){
		exercicio = new Exercicio();
		exercicio.setNome("nomeExercicio");
		exercicio.setDescricao("descricaoExercicio");
		exercicio.setGrupamento(GrupoMuscularEnum.Bíceps);
		
		Exercicio exercicioSalvo = exercicioRep.save(exercicio);
		
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
		
		treinoRep.save(treino);
	}
	
	@Test
	@Transactional
	public void salvar(){
		assertNotNull(treino.getId());
	}
	
	@Test
	public void editar(){
		Treino novoTreino = treinoRep.findOne(treino.getId());
		novoTreino.setNome("nomeNovoTreino");
		Treino treinoEditado = treinoRep.save(novoTreino);
		assertNotEquals(treino, treinoEditado);
	}
	
	@Test
	@Transactional
	public void remover() throws Exception{
		treinoRep.delete(treino);
		Treino treinoExcluido = treinoRep.findOne(treino.getId());
		assertEquals(treinoExcluido, null);
	}
	
	@Test
	public void buscarTodos(){
		List<Treino> todosTreinos = treinoRep.findAll();
		assertTrue(todosTreinos.size() > 0);		
	}
	
	@Autowired
	private AcademiaRepository academiaRep;
	
	@Test
	public void buscarPorAcademia(){
		Academia academia = new Academia();
		academia.setNomeFantasiaEmpresarial("A");
		academiaRep.save(academia);
		
		Treino treino = new Treino();
		Treino treino1 = new Treino();
		Treino treino2 = new Treino();
		
		treino.setAcademia(academia);
		treino1.setAcademia(academia);
		treino2.setAcademia(academia);
		
		treinoRep.save(treino);
		treinoRep.save(treino1);
		treinoRep.save(treino2);
		
		List<Treino> treinos = treinoRep.findByAcademia(academia);
		
		assertTrue(treinos.size() == 3);
	}
}
