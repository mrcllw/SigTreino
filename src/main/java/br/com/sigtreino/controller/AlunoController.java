package br.com.sigtreino.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigtreino.model.Aluno;
import br.com.sigtreino.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoSer;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aluno> adicionarAluno(@RequestBody Aluno aluno){
		Aluno alunoAdicionado = alunoSer.salvar(aluno);
		return new ResponseEntity<>(alunoAdicionado, HttpStatus.OK);
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Aluno>> buscarTodosAlunos(){
		Collection<Aluno> alunosBuscados = alunoSer.buscarTodos();
		return new ResponseEntity<>(alunosBuscados, HttpStatus.OK);
	}
		
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Aluno> removerAluno(@PathVariable Long id){
		Aluno alunoEncontrado = alunoSer.buscarPorId(id);
		if(alunoEncontrado == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		alunoSer.excluir(alunoEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
//	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Collection<Aluno>> buscarAlunosPorAcademia(){
//		Collection<Aluno> alunosPorAcademia = alunoSer.buscarPorAcademia();
//		return new ResponseEntity<>(alunosPorAcademia, HttpStatus.OK);
//	}

}
