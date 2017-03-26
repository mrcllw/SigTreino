package br.com.sigtreino.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.model.Aluno;
import br.com.sigtreino.repository.AcademiaRepository;
import br.com.sigtreino.repository.AlunoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoTeste {

	@Autowired
	private AlunoRepository alunoRep;
	
	private Aluno aluno;
	
	@Before
	public void init(){
		aluno = new Aluno();
		aluno.setAcademia(null);
		aluno.setBairro("bairro");
		aluno.setCep(1);
		aluno.setCidade("cidade");
		aluno.setComplemento("complemento");
		aluno.setCpfCnpj("CPF");
		Calendar data = Calendar.getInstance();
		aluno.setDataAberturaNascimento(data.getTime());
		aluno.setEmail("email");
		aluno.setEstado("estado");
		aluno.setLogradouro("logradouro");
		aluno.setNome("nome");
		aluno.setNumero("numero");
		aluno.setTelefone("telefone");
		
		alunoRep.save(aluno);
	}
	
	@Test
	public void salvar(){
		assertNotNull(aluno.getId());
	}
	
	@Test
	public void editar(){
		Aluno novoAluno = alunoRep.findOne(aluno.getId());
		novoAluno.setBairro("bairroNovoAluno");
		Aluno novoAlunoEditado = alunoRep.save(novoAluno);
		assertNotEquals(aluno, novoAlunoEditado);
	}
	
	@Test
	public void remover(){
		alunoRep.delete(aluno);
		Aluno alunoExcluido = alunoRep.findOne(aluno.getId());
		assertEquals(alunoExcluido, null);
	}
	
	@Test
	public void buscarTodos(){
		List<Aluno> todosAlunos = alunoRep.findAll();
		assertTrue(todosAlunos.size() > 0);
	}
	
	@Autowired
	private AcademiaRepository academiaRep;
	
	@Test
	public void buscarPorAcademia(){
		Academia academia = new Academia();
		academia.setNomeFantasiaEmpresarial("A");
		
		academiaRep.save(academia);
		
		Aluno aluno = new Aluno();
		Aluno aluno1 = new Aluno();
		Aluno aluno2 = new Aluno();
		
		aluno.setAcademia(academia);
		aluno1.setAcademia(academia);
		aluno2.setAcademia(academia);
		
		alunoRep.save(aluno);
		alunoRep.save(aluno1);
		alunoRep.save(aluno2);
		
		List<Aluno> alunos = alunoRep.findByAcademia(academia);
		
		assertTrue(alunos.size() == 3);
	}
}
