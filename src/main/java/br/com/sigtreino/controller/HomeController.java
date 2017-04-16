package br.com.sigtreino.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(){
		return "index";
	}
	
	@RequestMapping(value="/admin/validar-token", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> verificarValidadeToken(){
		return new ResponseEntity<>(HttpStatus.OK);
	};
}
