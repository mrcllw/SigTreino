package br.com.sigtreino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.service.AcademiaService;

@RestController
@RequestMapping("/academia")
public class AcademiaController {
	
	@Autowired
	private AcademiaService academiaSer;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Academia> adicionarAcademia(@RequestBody Academia academia){
		Academia academiaAdicionada = academiaSer.salvar(academia);
		return new ResponseEntity<>(academiaAdicionada, HttpStatus.OK);
	}

}
