package br.com.sigtreino.controller;

import java.util.Collection;

import javax.servlet.ServletException;

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

import br.com.sigtreino.exception.ErroException;
import br.com.sigtreino.model.Treino;
import br.com.sigtreino.service.TreinoService;

@RestController
@RequestMapping("/admin/treino")
public class TreinoController {
	
	@Autowired
	private TreinoService treinoSer;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Treino> adicionarTreino(@RequestBody Treino treino){
		Treino treinoAdicionado = treinoSer.salvar(treino);
		return new ResponseEntity<>(treinoAdicionado, HttpStatus.OK);
	}
	
//	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Collection<Treino>> buscarTodosTreinos(){
//		Collection<Treino> treinosBuscados = treinoSer.buscarTodos();
//		return new ResponseEntity<>(treinosBuscados, HttpStatus.OK);
//	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Treino> removerTreino(@PathVariable Long id) throws Exception{
		Treino treinoEncontrado = treinoSer.buscarPorId(id);
		if(treinoEncontrado == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try{
			treinoSer.excluir(treinoEncontrado);
		} catch(Exception e){
			throw new ErroException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Treino>> buscarTreinoPorAcademia() throws ServletException {
		Collection<Treino> treinosBuscados = treinoSer.buscarPorAcademia();
		return new ResponseEntity<>(treinosBuscados, HttpStatus.OK);
	}

}
