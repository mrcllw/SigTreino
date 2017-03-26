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

import br.com.sigtreino.model.AlunoTreino;
import br.com.sigtreino.service.AlunoTreinoService;

@RestController
@RequestMapping("/aluno/treino")
public class AlunoTreinoController {
	
	@Autowired
	private AlunoTreinoService alunoTreinoSer;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlunoTreino> adicionarAlunoTreino(@RequestBody AlunoTreino alunoTreino){
		AlunoTreino alunoTreinoAdicionado = alunoTreinoSer.salvar(alunoTreino);
		return new ResponseEntity<>(alunoTreinoAdicionado, HttpStatus.OK);
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AlunoTreino>> buscarTodosAlunoTreino(){
		Collection<AlunoTreino> alunoTreinoBuscados = alunoTreinoSer.buscarTodos();
		return new ResponseEntity<>(alunoTreinoBuscados, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<AlunoTreino> removerAlunoTreino(@PathVariable Long id){
		AlunoTreino alunoTreinoBuscado = alunoTreinoSer.buscarPorId(id);
		if(alunoTreinoBuscado == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		alunoTreinoSer.excluir(alunoTreinoBuscado);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
