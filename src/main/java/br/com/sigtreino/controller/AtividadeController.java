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

import br.com.sigtreino.model.Atividade;
import br.com.sigtreino.service.AtividadeService;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {
	
	@Autowired
	private AtividadeService atividadeSer;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Atividade> adicionarAtividade(@RequestBody Atividade atividade){
		Atividade atividadeAdicionada = atividadeSer.salvar(atividade);
		return new ResponseEntity<>(atividadeAdicionada, HttpStatus.OK);
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Atividade>> buscarTodasAtividade(){
		Collection<Atividade> atividadesBuscadas = atividadeSer.buscarTodos();
		return new ResponseEntity<>(atividadesBuscadas, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Atividade> removerAtividade(@PathVariable Long id){
		Atividade atividadeEncontrada = atividadeSer.buscarPorId(id);
		if(atividadeEncontrada == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		atividadeSer.excluir(atividadeEncontrada);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
