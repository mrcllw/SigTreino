package br.com.sigtreino.exception;

import org.springframework.http.HttpStatus;

public class ErroException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	private HttpStatus errorStatus;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public HttpStatus getErrorStatus() {
		return errorStatus;
	}
	
	public ErroException(){
		super();
	}
	
	public ErroException(String message, HttpStatus status){
		super();
		this.errorMessage = message;
		this.errorStatus = status;
	}

}
