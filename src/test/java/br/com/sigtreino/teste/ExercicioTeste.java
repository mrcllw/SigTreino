package br.com.sigtreino.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sigtreino.enums.GrupoMuscularEnum;
import br.com.sigtreino.model.Academia;
import br.com.sigtreino.model.Exercicio;
import br.com.sigtreino.repository.AcademiaRepository;
import br.com.sigtreino.repository.ExercicioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExercicioTeste {
	
	@Autowired
	private ExercicioRepository exercicioRep;
	
	private Exercicio exercicio;
	
	@Before
	public void init(){
		exercicio = new Exercicio();
		exercicio.setNome("nome");
		exercicio.setDescricao("descricao");
		exercicio.setGrupamento(GrupoMuscularEnum.Bíceps);
		
		exercicioRep.save(exercicio);
	}
	
	@Test
	public void salvar(){
		assertNotNull(exercicio.getId());
	}
	
	@Test
	public void editar(){
		Exercicio novoExercicio = exercicioRep.findOne(exercicio.getId());
		novoExercicio.setNome("novoExercicioNome");
		Exercicio novoExercicioEditado = exercicioRep.save(novoExercicio);
		assertNotEquals(exercicio, novoExercicioEditado);
	}
	
	@Test
	public void remover() throws Exception{
		exercicioRep.delete(exercicio);
		Exercicio exercicioExcluido = exercicioRep.findOne(exercicio.getId());
		assertEquals(exercicioExcluido, null);
	}
	
	@Test
	public void buscarTodos(){
		List<Exercicio> todosExercicios = exercicioRep.findAll();
		assertTrue(todosExercicios.size() > 0);	
	}
	
	@Autowired
	private AcademiaRepository academiaRep;
	
	@Test
	public void buscarPorAcademia(){
		Academia academia = new Academia();
		academia.setNomeFantasiaEmpresarial("A");
		academiaRep.save(academia);
		
		Exercicio exercicio = new Exercicio();
		Exercicio exercicio1 = new Exercicio();
		Exercicio exercicio2 = new Exercicio();
		
		exercicio.setAcademia(academia);
		exercicio1.setAcademia(academia);
		exercicio2.setAcademia(academia);
		
		exercicioRep.save(exercicio);
		exercicioRep.save(exercicio1);
		exercicioRep.save(exercicio2);
		
		List<Exercicio> exercicios = exercicioRep.findByAcademia(academia);
		
		assertTrue(exercicios.size() == 3);
	}

}
