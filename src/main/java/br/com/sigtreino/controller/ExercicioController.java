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

import br.com.sigtreino.model.Exercicio;
import br.com.sigtreino.service.ExercicioService;

@RestController
@RequestMapping("/exercicio")
public class ExercicioController {
	
	@Autowired
	private ExercicioService exercicioSer;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Exercicio> adicionarExercicio(@RequestBody Exercicio exercicio){
		Exercicio exercicioAdicionado = exercicioSer.salvar(exercicio);
		return new ResponseEntity<>(exercicioAdicionado, HttpStatus.OK);
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Exercicio>> buscarTodosExercicios(){
		Collection<Exercicio> exerciciosBuscados = exercicioSer.buscarTodos();
		return new ResponseEntity<>(exerciciosBuscados, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Exercicio> removerExercicio(@PathVariable Long id) throws Exception{
		Exercicio exercicioEncontrado = exercicioSer.buscarPorId(id);
		if(exercicioEncontrado == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		exercicioSer.excluir(exercicioEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
//	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Collection<Exercicio>> buscarTodosExerciciosPorAcademia(){
//		Collection<Exercicio> exerciciosBuscados = exercicioSer.buscarPorAcademia();
//		return new ResponseEntity<>(exerciciosBuscados, HttpStatus.OK);
//	}

}
