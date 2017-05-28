package br.com.sigtreino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigtreino.service.CEPService;

@RestController
public class CEPController {
	
	@Autowired
	private CEPService cepService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE, value="/cep/{cep}")
	public ResponseEntity<String> consultarCep(@PathVariable String cep) throws Exception{
		String cepEncontrado = cepService.consultarCEP(cep);
		return new ResponseEntity<String>(cepEncontrado, HttpStatus.OK);
	}
}
