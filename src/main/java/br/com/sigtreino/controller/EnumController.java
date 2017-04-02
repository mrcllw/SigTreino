package br.com.sigtreino.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.sigtreino.enums.AtividadeFisicaEnum;
import br.com.sigtreino.enums.DiaTreinoEnum;
import br.com.sigtreino.enums.GrupoMuscularEnum;
import br.com.sigtreino.enums.TipoTreinoEnum;

@Controller
public class EnumController {

	@GetMapping(value="/dias-treino", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DiaTreinoEnum[]> exibirDiasTreino(){
		return new ResponseEntity<>(DiaTreinoEnum.values(), HttpStatus.OK);
	}
	
	@GetMapping(value="/grupo-muscular", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GrupoMuscularEnum[]> exibirGruposMuscular(){
		return new ResponseEntity<>(GrupoMuscularEnum.values(), HttpStatus.OK);
	}
	
	@GetMapping(value="/tipo-treino", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoTreinoEnum[]> exibirTiposTreino(){
		return new ResponseEntity<>(TipoTreinoEnum.values(), HttpStatus.OK);
	}
	
	@GetMapping(value="/atividade-fisica", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AtividadeFisicaEnum[]> exibirAtividadesFisica(){
		return new ResponseEntity<>(AtividadeFisicaEnum.values(), HttpStatus.OK);
	}
}
