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
import br.com.sigtreino.repository.AcademiaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademiaTeste {

	@Autowired
	private AcademiaRepository academiaRep;
	
	private Academia academia;
	
	@Before
	public void init(){
		academia = new Academia();
		academia.setBairro("bairro");
		academia.setCep(1);
		academia.setCidade("cidade");
		academia.setComplemento("complemento");
		academia.setCpfCnpj("cpfCnpj");
		Calendar data = Calendar.getInstance();
		academia.setDataAberturaNascimento(data.getTime());
		academia.setEmail("email");
		academia.setEstado("estado");
		academia.setLogin("login");
		academia.setLogradouro("logradouro");
		academia.setNomeFantasiaEmpresarial("nomeFantasiaEmpresarial");
		academia.setNumero("numero");
		academia.setRazaoSocialNomeEmpresario("razaoSocialNomeEmpresario");
		academia.setSenha("senha");
		academia.setTelefone("telefone");
		
		academiaRep.save(academia);
	}
	
	@Test
	public void salvar(){
		assertNotNull(academia.getId());
	}
	
	@Test
	public void editar(){
		Academia novaAcademia = academiaRep.findOne(academia.getId());
		novaAcademia.setBairro("bairroNovaAcademia");
		Academia novaAcademiaEditada = academiaRep.save(novaAcademia);
		assertNotEquals(academia,novaAcademiaEditada);
	}
	
	@Test
	public void remover(){
		academiaRep.delete(academia);
		Academia academiaExcluida = academiaRep.findOne(academia.getId());
		assertEquals(academiaExcluida, null);
	}
	
	@Test
	public void buscarTodos(){
		List<Academia> todasAcademias = academiaRep.findAll();
		assertTrue(todasAcademias.size() > 0);
	}
}
