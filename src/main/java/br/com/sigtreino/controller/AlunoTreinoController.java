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
import org.springframework.web.bind.annotation.RestController;

import br.com.sigtreino.exception.ErroException;
import br.com.sigtreino.model.AlunoTreino;
import br.com.sigtreino.service.AlunoTreinoService;

@RestController
public class AlunoTreinoController {
	
	@Autowired
	private AlunoTreinoService alunoTreinoSer;
	
	@PostMapping(value="/admin/aluno/treino", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlunoTreino> adicionarAlunoTreino(@RequestBody AlunoTreino alunoTreino){
		AlunoTreino alunoTreinoAdicionado = alunoTreinoSer.salvar(alunoTreino);
		return new ResponseEntity<>(alunoTreinoAdicionado, HttpStatus.OK);
	}
	
//	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Collection<AlunoTreino>> buscarTodosAlunoTreino(){
//		Collection<AlunoTreino> alunoTreinoBuscados = alunoTreinoSer.buscarTodos();
//		return new ResponseEntity<>(alunoTreinoBuscados, HttpStatus.OK);
//	}
	
	@DeleteMapping(value="/admin/aluno/treino/{id}")
	public ResponseEntity<AlunoTreino> removerAlunoTreino(@PathVariable Long id){
		AlunoTreino alunoTreinoBuscado = alunoTreinoSer.buscarPorId(id);
		if(alunoTreinoBuscado == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		alunoTreinoSer.excluir(alunoTreinoBuscado);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/admin/aluno/treino/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AlunoTreino>> buscarAlunoTreinoPorAlunoId(@PathVariable Long id){
		Collection<AlunoTreino> alunoTreinoBuscado = alunoTreinoSer.buscarPorAluno(id);
		return new ResponseEntity<>(alunoTreinoBuscado, HttpStatus.OK);
	}
	
	@GetMapping(value="/aluno/treino/{email}/", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AlunoTreino>> buscarAlunoTreinoPorEmail(@PathVariable String email) throws ErroException{
		Collection<AlunoTreino> alunoTreinoBuscado;
		
		try {
			alunoTreinoBuscado = alunoTreinoSer.buscarPorEmail(email);
		} catch(Exception e){
			throw new ErroException(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(alunoTreinoBuscado, HttpStatus.OK);
	}

}
