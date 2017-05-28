package br.com.sigtreino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigtreino.exception.ErroException;
import br.com.sigtreino.service.CPFCNPJService;

@RestController
public class CPFCNPJController {
	
	@Autowired
	private CPFCNPJService cpfCnpjService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE, value="/cnpj/{cnpj}")
	public ResponseEntity<String> consultarCnpj(@PathVariable String cnpj) throws Exception{
		String cnpjEncontrado = cpfCnpjService.consultaCNPJ(cnpj);
		return new ResponseEntity<String>(cnpjEncontrado, HttpStatus.OK);
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE, value="/cpf/{cpf}")
	public ResponseEntity<?> consultarCpf(@PathVariable String cpf) throws Exception{
		try{
			cpfCnpjService.consultaCPF(cpf);
		}catch(Exception e){
			throw new ErroException(e.getMessage(), HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
