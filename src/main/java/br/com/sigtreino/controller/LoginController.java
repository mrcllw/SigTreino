package br.com.sigtreino.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.service.AcademiaService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {

	@Autowired
	private AcademiaService academiaSer;
	
	@PostMapping(value="/autenticar", consumes=MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse autenticar (@RequestBody Academia academia) throws ServletException {
		if(academia.getLogin() == null || academia.getSenha() == null){
			throw new ServletException("Nome e senha obrigatórios.");
		}
		Academia academiaAutenticada = academiaSer.buscarPorLogin(academia.getLogin());
		
		if(academiaAutenticada == null){
			throw new ServletException("Usuario não encontrado.");
		}
		
		if(!academiaAutenticada.getSenha().equals(academia.getSenha())){
			throw new ServletException("Usuario ou senha invalida.");
		}
		
		Gson gson = new Gson();
		String academiaJson = gson.toJson(academiaAutenticada);
		
		String token = Jwts.builder()
				.setSubject(academiaJson)
				.signWith(SignatureAlgorithm.HS512, "chave")
				.setExpiration(new Date(System.currentTimeMillis() + 60 * 60000))
				.compact();
		return new LoginResponse(token);
	}
	
	private class LoginResponse {
		public String token;
		
		public LoginResponse(String token){
			this.token = token;
		}
	}
}
