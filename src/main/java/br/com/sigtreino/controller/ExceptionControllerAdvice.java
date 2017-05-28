package br.com.sigtreino.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sigtreino.exception.ErroException;
import br.com.sigtreino.exception.ErroResponse;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(ErroException.class)
	public ResponseEntity<ErroResponse> exceptionHandler(ErroException ex){
		ErroResponse error = new ErroResponse();
		error.setMessage(ex.getErrorMessage());
		return new ResponseEntity<ErroResponse>(error, ex.getErrorStatus());
	}

}
