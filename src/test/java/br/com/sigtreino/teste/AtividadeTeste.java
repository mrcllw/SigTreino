package br.com.sigtreino.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sigtreino.enums.GrupoMuscularEnum;
import br.com.sigtreino.model.Atividade;
import br.com.sigtreino.model.Exercicio;
import br.com.sigtreino.repository.AtividadeRepository;
import br.com.sigtreino.repository.ExercicioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtividadeTeste {

	@Autowired
	private AtividadeRepository atividadeRep;
	
	@Autowired
	private ExercicioRepository exercicioRep;
	
	private Exercicio exercicio;
	private Atividade atividade;
	
	@Before
	public void init(){
		exercicio = new Exercicio();
		exercicio.setNome("nome");
		exercicio.setDescricao("descricao");
		exercicio.setGrupamento(GrupoMuscularEnum.Bíceps);
		
		Exercicio exercicioSalvo = exercicioRep.save(exercicio);
		
		atividade = new Atividade();
		atividade.setExercicio(exercicioSalvo);
		atividade.setSerie(3);
		atividade.setRepeticao(10);
		
		atividadeRep.save(atividade);
	}
	
	@Test
	@Transactional
	public void salvar(){
		assertNotNull(atividade.getId());
	}
	
	@Test
	public void editar(){
		Atividade novaAtividade = atividadeRep.findOne(atividade.getId());
		novaAtividade.setRepeticao(20);
		Atividade novaAtividadeEditada = atividadeRep.save(novaAtividade);
		assertNotEquals(atividade, novaAtividadeEditada);
	}
	
	@Test
	@Transactional
	public void remover(){
		atividadeRep.delete(atividade);
		Atividade atividadeExcluida = atividadeRep.findOne(atividade.getId());
		assertEquals(atividadeExcluida, null);
	}
	
	@Test
	public void buscarTodos(){
		List<Atividade> todasAtividades = atividadeRep.findAll();
		assertTrue(todasAtividades.size() > 0);
	}
}